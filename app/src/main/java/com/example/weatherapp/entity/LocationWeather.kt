package com.example.weatherapp.entity

data class LocationWeather(val weather: List<Map<String, Any>>, val main: Map<String, Float>)