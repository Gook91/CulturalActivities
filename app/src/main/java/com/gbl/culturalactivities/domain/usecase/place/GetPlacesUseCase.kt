package com.gbl.culturalactivities.domain.usecase.place

import com.gbl.culturalactivities.domain.repository.PlaceRepository
import javax.inject.Inject

class GetPlacesUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
) {
    suspend operator fun invoke(): List<String> = placeRepository.getAllPlaces()
}