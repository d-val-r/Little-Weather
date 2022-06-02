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

    // the mutable packing property
    private var _response = MutableLiveData<String>()

    // the immutable exposed property
    val response: LiveData<String>
        get() = _response



    fun getWeather(location: String) {

        viewModelScope.launch {
            try {
                val locResult = LocationApi.retrofitService.getLocationFromAPI(
                    location,
                    1,
                    "cf60e2c6735463e6bda22d6c8ab3d444")
                    lat = locResult[0].lat
                    lon = locResult[0].lon

                val weatherResult = WeatherApi.retrofitService.getWeatherFromAPI(lat, lon, "")
                Log.i("testing", lat.toString() + " " + lon.toString())
                _response.value = weatherResult.main["temp"].toString()

            } catch (e: Exception) {
                _response.value = "Invalid response from server. Please make sure you are spelling everything correctly."
            }
        }
    }

}