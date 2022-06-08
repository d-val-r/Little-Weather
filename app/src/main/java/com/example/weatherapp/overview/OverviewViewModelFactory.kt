package com.example.weatherapp.overview

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.database.WeatherDao

class OverviewViewModelFactory(
    private val application: Application,
    private val database: WeatherDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OverviewViewModel::class.java)) {
            return OverviewViewModel(application, database) as T
        }

        throw IllegalArgumentException("Unknown ViewModelClass")
    }
}