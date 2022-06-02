package com.example.weatherapp.overview


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.entity.Location
import com.example.weatherapp.network.WeatherApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverviewViewModel : ViewModel() {

    // the mutable packing property
    private var _response = MutableLiveData<String>()

    // the immutable exposed property
    val response: LiveData<String>
        get() = _response



    fun getWeather(location: String) {
//        WeatherApi.retrofitService.getWeatherFromAPI(location, 1, "").enqueue(
//            object: Callback<List<Location>> {
//                override fun onResponse(call: Call<List<Location>>, response: Response<List<Location>>) {
//                    _response.value = response.body()?.get(0)?.name + ": " + response.body()?.get(0)?.lat +
//                            " " + response.body()?.get(0)?.lon
//                }
//
//                override fun onFailure(call: Call<List<Location>>, t: Throwable) {
//                    _response.value = "Failure: " + t.message
//                }
//            }
//        )

        viewModelScope.launch {
            try {
                val result = WeatherApi.retrofitService.getWeatherFromAPI(
                    location,
                    1,
                    "")
                _response.value = result[0].name + ": " + result[0].lat +
                            " " + result[0].lon

            } catch (e: Exception) {
                _response.value = "Invalid response from server. Please make sure you are spelling everything correctly."
            }
        }
    }

}