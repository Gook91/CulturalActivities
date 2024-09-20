package com.gbl.culturalactivities.ui.screens.activityinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbl.culturalactivities.domain.repository.CulturalActivityRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = CulturalActivityInfoViewModel.Factory::class)
class CulturalActivityInfoViewModel @AssistedInject constructor(
    @Assisted private val culturalActivityId: Int? = null,
    private val culturalActivityRepository: CulturalActivityRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(CulturalActivityUiState())
    val uiState: StateFlow<CulturalActivityUiState> get() = _uiState

    init {
        culturalActivityId?.let {
            viewModelScope.launch {
                val culturalActivity =
                    culturalActivityRepository.getCulturalActivity(culturalActivityId)
                _uiState.value = CulturalActivityUiState(culturalActivity)
            }
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