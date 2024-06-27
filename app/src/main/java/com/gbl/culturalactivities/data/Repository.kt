package com.gbl.culturalactivities.data

import com.gbl.culturalactivities.data.db.CulturalActivitiesDao
import com.gbl.culturalactivities.entity.CulturalActivity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val culturalActivitiesDao: CulturalActivitiesDao) {

    fun getCulturalActivitiesList(): Flow<List<CulturalActivity>> =
        culturalActivitiesDao.getAllActivities()

    suspend fun putCulturalActivity(culturalActivity: CulturalActivity) =
        culturalActivitiesDao.upsert(culturalActivity)

    suspend fun getCulturalActivity(id: Int): CulturalActivity =
        culturalActivitiesDao.getActivityById(id)

    suspend fun deleteCulturalActivity(id: Int) =
        culturalActivitiesDao.delete(id)

}