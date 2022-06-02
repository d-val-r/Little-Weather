package com.example.weatherapp.overview


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.entity.LocationWeather
import com.example.weatherapp.network.WeatherApi
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
        WeatherApi.retrofitService.getWeatherFromAPI(location, 1, "").enqueue(
            object: Callback<List<LocationWeather>> {
                override fun onResponse(call: Call<List<LocationWeather>>, response: Response<List<LocationWeather>>) {
                    _response.value = response.body()?.get(0)?.name + " " + response.body()?.get(0)?.lat +
                            " " + response.body()?.get(0)?.lon
                }

                override fun onFailure(call: Call<List<LocationWeather>>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }
            }
        )
    }

}