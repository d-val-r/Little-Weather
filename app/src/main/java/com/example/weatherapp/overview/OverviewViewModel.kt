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



    fun getWeather() {
        WeatherApi.retrofitService.getWeatherFromAPI().enqueue(
            object: Callback<LocationWeather> {
                override fun onResponse(call: Call<LocationWeather>, response: Response<LocationWeather>) {
                    _response.value = response.body()?.conditions?.get(0)?.main?.get("temp").toString()
                }

                override fun onFailure(call: Call<LocationWeather>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }
            }
        )
    }

}