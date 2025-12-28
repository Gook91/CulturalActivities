package com.gbl.culturalactivities.domain.usecase

import com.gbl.culturalactivities.domain.entity.CulturalActivity
import com.gbl.culturalactivities.domain.repository.CulturalActivityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilteredActivityListUseCase @Inject constructor(
    private val culturalActivityRepository: CulturalActivityRepository,
) {
    operator fun invoke(): Flow<List<CulturalActivity>> {
        return culturalActivityRepository.getCulturalActivitiesList()
    }
}