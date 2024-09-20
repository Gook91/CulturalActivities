package com.gbl.culturalactivities.data

import com.gbl.culturalactivities.data.db.CulturalActivitiesDao
import com.gbl.culturalactivities.domain.repository.CulturalActivityRepository
import com.gbl.culturalactivities.domain.entity.CulturalActivity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CulturalActivityRepositoryImpl @Inject constructor(
    private val culturalActivitiesDao: CulturalActivitiesDao
): CulturalActivityRepository {

    override fun getCulturalActivitiesList(): Flow<List<CulturalActivity>> =
        culturalActivitiesDao.getAllActivities()

    override suspend fun putCulturalActivity(culturalActivity: CulturalActivity) =
        culturalActivitiesDao.upsert(culturalActivity)

    override suspend fun getCulturalActivity(id: Int): CulturalActivity =
        culturalActivitiesDao.getActivityById(id)

    override suspend fun deleteCulturalActivity(id: Int) =
        culturalActivitiesDao.delete(id)

}