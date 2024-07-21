package com.gbl.culturalactivities.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.gbl.culturalactivities.entity.CulturalActivity
import kotlinx.coroutines.flow.Flow

@Dao
interface CulturalActivitiesDao {
    @Upsert
    suspend fun upsert(culturalActivityDto: CulturalActivityDto)

    suspend fun upsert(culturalActivity: CulturalActivity) {
        val culturalActivityDto = CulturalActivityDto(culturalActivity)
        upsert(culturalActivityDto)
    }

    @Query("SELECT * FROM activities ORDER BY " +
            "dateOfVisit NOT NULL DESC, dateOfVisit, " +
            "endingDate NOT NULL DESC, endingDate")
    fun getAllActivities(): Flow<List<CulturalActivityDto>>

    @Query("SELECT * FROM activities WHERE id=:id")
    suspend fun getActivityById(id: Int): CulturalActivityDto

    @Query("DELETE FROM activities WHERE id=:id")
    suspend fun delete(id: Int)
}