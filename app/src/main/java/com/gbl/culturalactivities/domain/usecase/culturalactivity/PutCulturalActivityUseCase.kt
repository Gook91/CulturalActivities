package com.gbl.culturalactivities.domain.usecase.culturalactivity

import com.gbl.culturalactivities.domain.entity.CulturalActivity
import com.gbl.culturalactivities.domain.repository.CulturalActivityRepository
import javax.inject.Inject

class PutCulturalActivityUseCase @Inject constructor(
    private val culturalActivityRepository: CulturalActivityRepository
) {
    suspend operator fun invoke(culturalActivity: CulturalActivity) =
        culturalActivityRepository.putCulturalActivity(culturalActivity)
}