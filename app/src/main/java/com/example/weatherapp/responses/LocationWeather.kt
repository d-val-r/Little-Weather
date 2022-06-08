package com.example.weatherapp.responses

data class LocationWeather(

    // JSON responses
    val weather: List<Map<String, Any>>,
    val main: Map<String, Float>,
    val name: String
)