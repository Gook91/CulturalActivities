package com.gbl.culturalactivities.domain.usecase.culturalactivity

import com.gbl.culturalactivities.domain.repository.CulturalActivityRepository
import javax.inject.Inject

class DeleteCulturalActivityUseCase @Inject constructor(
    private val culturalActivityRepository: CulturalActivityRepository
) {
    suspend operator fun invoke(id: Int) =
        culturalActivityRepository.deleteCulturalActivity(id)
}