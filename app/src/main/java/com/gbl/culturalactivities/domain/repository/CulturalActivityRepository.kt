package com.gbl.culturalactivities.domain.repository

import com.gbl.culturalactivities.entity.CulturalActivity
import kotlinx.coroutines.flow.Flow

interface CulturalActivityRepository {

    fun getCulturalActivitiesList(): Flow<List<CulturalActivity>>

    suspend fun putCulturalActivity(culturalActivity: CulturalActivity)

    suspend fun getCulturalActivity(id: Int): CulturalActivity

    suspend fun deleteCulturalActivity(id: Int)
}