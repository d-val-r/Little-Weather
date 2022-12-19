package com.example.weatherapp.entity

import android.view.View
import kotlin.math.roundToInt
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
    val temperature: Double,

    @ColumnInfo(name = "hmdty")
    val humidity: Double
) {
    @PrimaryKey(autoGenerate = false)
    var ID = "${cityName},${stateName},${countryName}"

    // converts the temperature to a Fahrenheit representation and stores it as a
    // string to render in a TextView; round to two decimal places
    var fTemp = ((((temperature - 273.15) * (9.0/5.0) + 32) * 100)
        .roundToInt() / 100.0).toString()

    // if the country name is the US set
    // the visibility variable to later be referenced by the TextView
    var stateVisibility = if (countryName == "US") View.VISIBLE else View.GONE
}