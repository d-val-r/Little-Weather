package com.example.weatherapp.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.database.WeatherDao
import com.example.weatherapp.entity.Entry
import kotlinx.coroutines.launch

class HomeViewModel(val dao: WeatherDao, application: Application) : AndroidViewModel(application) {

    private var _locList = dao.getAllEntries()
    val locList: LiveData<List<Entry>>
        get() = _locList

    init {
        _locList = dao.getAllEntries()
    }

    fun clear() {
        viewModelScope.launch {
            dao.deleteAllEntries()
        }
    }
}