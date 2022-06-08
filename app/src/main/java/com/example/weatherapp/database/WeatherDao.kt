package com.example.weatherapp.database

import androidx.room.*
import com.example.weatherapp.entity.Entry

@Dao
interface WeatherDao {

    @Insert
    suspend fun insert(entry: Entry)

    @Update
    suspend fun update(entry: Entry)

    @Query("SELECT * from locations_table WHERE cityName = :name")
    suspend fun get(name: String): Entry?

    @Query("DELETE from locations_table WHERE cityName = :name")
    suspend fun delete(name: String)

    @Query("SELECT * from locations_table")
    suspend fun getAllEntries(): List<Entry?>

    @Query("DELETE from locations_table")
    suspend fun deleteAllEntries()
}