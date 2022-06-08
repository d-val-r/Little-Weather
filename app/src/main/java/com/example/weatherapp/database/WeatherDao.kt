package com.example.weatherapp.database

import androidx.room.*
import com.example.weatherapp.entity.Entry

@Dao
interface WeatherDao {

    @Insert
    suspend fun insert(entry: Entry)

    @Update
    fun update(entry: Entry)

    @Query("SELECT * from locations_table WHERE cityName = :name")
    fun get(name: String): Entry?

    @Query("DELETE from locations_table WHERE cityName = :name")
    fun delete(name: String)

}