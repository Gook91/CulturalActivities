package com.gbl.culturalactivities.ui.screens.activityinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbl.culturalactivities.domain.repository.CulturalActivityRepository
import com.gbl.culturalactivities.domain.repository.PlaceRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = CulturalActivityInfoViewModel.Factory::class)
class CulturalActivityInfoViewModel @AssistedInject constructor(
    @Assisted private val culturalActivityId: Int? = null,
    private val culturalActivityRepository: CulturalActivityRepository,
    private val placeRepository: PlaceRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(CulturalActivityUiState())
    val uiState: StateFlow<CulturalActivityUiState> get() = _uiState

    init {
        viewModelScope.launch {
            val culturalActivityDeferred = async {
                culturalActivityId?.let { culturalActivityRepository.getCulturalActivity(it) }
            }
            val placesDeferred = async {
                placeRepository.getAllPlaces()
            }
            val culturalActivity = culturalActivityDeferred.await()
            val places = placesDeferred.await()
            _uiState.value = CulturalActivityUiState(culturalActivity, places)
        }
    }

    fun saveCulturalActivity() {
        viewModelScope.launch {
            culturalActivityRepository.putCulturalActivity(uiState.value.culturalActivity)
        }
    }

    fun deleteCulturalActivity() {
        viewModelScope.launch {
            culturalActivityId?.let { culturalActivityRepository.deleteCulturalActivity(it) }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(culturalActivityId: Int?): CulturalActivityInfoViewModel
    }
}