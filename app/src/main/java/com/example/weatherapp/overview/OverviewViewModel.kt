package com.example.weatherapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
            object: Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    _response.value = response.body()
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }
            }
        )
    }

}