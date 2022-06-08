package com.example.weatherapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations_table")
data class Entry(
    @PrimaryKey(autoGenerate = false)
    val cityName: String,

    @ColumnInfo(name = "state")
    val stateName: String,

    @ColumnInfo(name = "country")
    val countryName: String
)