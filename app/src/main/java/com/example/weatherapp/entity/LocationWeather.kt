package com.example.weatherapp.entity

import com.squareup.moshi.Json

data class LocationWeather(
    @Json(name = "list") val conditions: List<Any>
) {
    val temp = conditions[0]
}