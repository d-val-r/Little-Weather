package com.example.weatherapp.overview


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.network.LocationApi
import com.example.weatherapp.network.WeatherApi
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {

    private var lat: Float = 0F
    private var lon: Float = 0F

    private val KEY = ""

    // the mutable packing property
    private var _response = MutableLiveData<List<String>>()

    // the immutable exposed property
    val response: LiveData<List<String>>
        get() = _response


    fun getWeather(location: String) {

        viewModelScope.launch {
            try {
                val locResult = LocationApi.retrofitService.getLocationFromAPI(
                    location,
                    1,
                    KEY)
                    lat = locResult[0].lat
                    lon = locResult[0].lon


                val weatherResult = WeatherApi.retrofitService.getWeatherFromAPI(lat, lon, KEY)
                Log.i("testing", lat.toString() + " " + lon.toString())
                _response.value = listOf(weatherResult.name, weatherResult.main["temp"].toString())

            } catch (e: Exception) {
                _response.value = listOf("failed", "Invalid response from server. Please make sure you are spelling everything correctly.")
            }
        }
    }

}