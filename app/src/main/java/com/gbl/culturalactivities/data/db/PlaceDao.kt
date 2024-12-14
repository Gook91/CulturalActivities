package com.gbl.culturalactivities.data.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PlaceDao {
    @Query("SELECT DISTINCT place FROM activities")
    suspend fun getAllPlaces(): List<String>
}