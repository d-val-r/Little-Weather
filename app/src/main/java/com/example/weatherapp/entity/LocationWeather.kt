package com.example.weatherapp.entity
import com.squareup.moshi.Json

// the main JSON response consists of nested JSON objects
// use this secondary class to load the JSON strings into objects
// with Moshi
data class ListItems(val dt: Int, val main: Map<String, Float>, val weather: List<Map<String, Any>>)

// the main JSON response
data class LocationWeather(
    @Json(name = "list") val conditions: List<ListItems>,
) {

}