package com.gbl.culturalactivities.ui.screens.activitieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbl.culturalactivities.domain.repository.CulturalActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CulturalActivitiesListViewModel @Inject constructor(
    culturalActivityRepository: CulturalActivityRepository
) : ViewModel() {
    val listFlow = culturalActivityRepository.getCulturalActivitiesList()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            emptyList()
        )
}