package com.gbl.culturalactivities.domain.repository

interface PlaceRepository {
    suspend fun getAllPlaces(): List<String>
}