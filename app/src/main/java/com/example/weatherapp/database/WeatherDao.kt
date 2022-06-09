package com.example.weatherapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.weatherapp.entity.Entry

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: Entry)

    @Update
    suspend fun update(entry: Entry)

    @Query("SELECT * from locations_table WHERE ID = :id")
    suspend fun get(id: String): Entry?

    @Query("DELETE from locations_table WHERE ID = :id")
    suspend fun delete(id: String)

    @Query("SELECT * from locations_table")
    fun getAllEntries(): LiveData<List<Entry>>

    @Query("DELETE from locations_table")
    suspend fun deleteAllEntries()
}