package com.gbl.culturalactivities.data

import com.gbl.culturalactivities.data.db.CulturalActivityDao
import com.gbl.culturalactivities.domain.repository.CulturalActivityRepository
import com.gbl.culturalactivities.domain.entity.CulturalActivity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CulturalActivityRepositoryImpl @Inject constructor(
    private val culturalActivityDao: CulturalActivityDao
): CulturalActivityRepository {

    override fun getCulturalActivitiesList(): Flow<List<CulturalActivity>> =
        culturalActivityDao.getAllActivities()

    override suspend fun putCulturalActivity(culturalActivity: CulturalActivity) =
        culturalActivityDao.upsert(culturalActivity)

    override suspend fun getCulturalActivity(id: Int): CulturalActivity =
        culturalActivityDao.getActivityById(id)

    override suspend fun deleteCulturalActivity(id: Int) =
        culturalActivityDao.delete(id)

}