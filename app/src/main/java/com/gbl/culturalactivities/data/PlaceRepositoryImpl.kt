package com.gbl.culturalactivities.data

import com.gbl.culturalactivities.data.db.PlaceDao
import com.gbl.culturalactivities.domain.repository.PlaceRepository
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    private val placeDao: PlaceDao
): PlaceRepository {
    override suspend fun getAllPlaces(): List<String> = placeDao.getAllPlaces()
}