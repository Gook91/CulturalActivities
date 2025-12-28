package com.gbl.culturalactivities.ui.screens.activitieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbl.culturalactivities.domain.usecase.GetFilteredActivityListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CulturalActivitiesListViewModel @Inject constructor(
    getFilteredActivityListUseCase: GetFilteredActivityListUseCase
) : ViewModel() {
    val listFlow = getFilteredActivityListUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            emptyList()
        )
}