package com.example.weatherapp.overview

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.weatherapp.database.WeatherDao
import com.example.weatherapp.entity.Entry
import com.example.weatherapp.network.LocationApi
import com.example.weatherapp.network.WeatherApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class OverviewViewModel(
    application: Application, private val database: WeatherDao
) : AndroidViewModel(application) {

    // values from the geocoding API
    // used to leverage the weather API
    private var lat: Float = 0F
    private var lon: Float = 0F

    // values populated by entries in the JSON response
    // will be used in creation of the database entry
    private var name: String = ""
    private var state: String = ""
    private var country: String = ""
    private var weather: String = ""
    private var temperature: Float = 0F
    private var humidity: Float = 0F

    private val KEY = ""

    // the mutable packing property
    private var _response = MutableLiveData<List<String>>()

    // the immutable exposed property
    val response: LiveData<List<String>>
        get() = _response


    fun getWeather(location: String) {

        viewModelScope.launch {
            try {

                // connect to the API
                val locResult = LocationApi.retrofitService.getLocationFromAPI(
                    location,
                    1,
                    KEY)[0]
                lat = locResult.lat
                lon = locResult.lon
                name = locResult.name
                state = locResult.state
                country = locResult.country


                // connect to the API
                val weatherResult = WeatherApi.retrofitService.getWeatherFromAPI(lat, lon, KEY)

                // set the live data response so that the search box displays the values
                _response.value = listOf(weatherResult.name, weatherResult.main["temp"].toString())

                // set the weather conditions, if available
                weather = weatherResult.weather[0]["main"].toString() ?: "N/A"
                temperature = weatherResult.main["temp"] ?: -999F
                humidity = weatherResult.main["humidity"] ?: -1F


            } catch (e: Exception) {
                _response.value = listOf("failed", "Invalid response from server. Please make sure you are spelling everything correctly.")
            }
        }
    }

    fun add() = runBlocking {
        if (name != "") {
            database.insert(Entry(name, state, country, weather, temperature, humidity))

            Log.i("Dbtest", database.get(name)?.cityName ?: "null")

            // reset the name so that the entry is not re-added before the user locates another
            // city
            name = ""
        }
    }

}