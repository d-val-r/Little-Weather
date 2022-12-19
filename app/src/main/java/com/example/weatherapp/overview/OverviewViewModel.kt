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
    private var weather_map: Map<String, Any> = mapOf<String, Any>()
    private var weather_list: ArrayList<Any> = ArrayList<Any>()
    private var weather: String = ""
    private var temperature: Double = 0.0
    private var humidity: Double = 0.0

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

                Log.i("loctest", "lat: ${lat}, lon: ${lon}")


                // connect to the API
                val weatherResult = WeatherApi.retrofitService.getWeatherFromAPI(lat, lon, KEY)


                // the JSON response returns the weather map in a list of length one;
                // must cast to temporary variable to track it
                weather_list = weatherResult.current["weather"] as ArrayList<Any>

                // cast the weather entry to a map
                weather_map = weather_list[0] as Map<String, Any>

                // get the weather data
                temperature = weatherResult.current["temp"] as Double
                humidity = weatherResult.current["humidity"] as Double

                // set the live data response so that the search box displays the values
                _response.value = listOf(name, weatherResult.current["temp"].toString())

            } catch (e: Exception) {
//                _response.value = listOf("failed", "Invalid response from server. Please make sure you are spelling everything correctly.")
                _response.value = listOf("failed", e.toString())
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