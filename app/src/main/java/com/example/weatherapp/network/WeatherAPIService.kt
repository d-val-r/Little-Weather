package com.example.weatherapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import com.example.weatherapp.entity.LocationWeather

private const val BASE_URL = "http://api.openweathermap.org/data/2.5/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface WeatherAPIService {
    @GET("forecast?id=524901&appid=")
    fun getWeatherFromAPI(): Call<LocationWeather>
}

object WeatherApi {
    val retrofitService : WeatherAPIService by lazy {
        retrofit.create(WeatherAPIService::class.java)
    }
}