package com.example.weatherapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// information from the class in the responses package will be consolidated
// into this class, which will be inserted into the database
@Entity(tableName = "locations_table")
data class Entry(
    @ColumnInfo(name = "city")
    val cityName: String,

    @ColumnInfo(name = "state")
    val stateName: String,

    @ColumnInfo(name = "country")
    val countryName: String,

    @ColumnInfo(name = "weather")
    val weather: String,

    @ColumnInfo(name = "temp")
    val temperature: Float,

    @ColumnInfo(name = "hmdty")
    val humidity: Float
) {
    @PrimaryKey(autoGenerate = false)
    var ID = "${cityName},${stateName},${countryName}"

    // converts the temperature to a Fahrenheit representation and stores it as a
    // string to render in a TextView
    var fTemp = ((temperature - 273.15) * (9.0/5.0) + 32).toString()
}