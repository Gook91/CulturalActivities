package com.gbl.culturalactivities.ui.screens.activityinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbl.culturalactivities.data.Repository
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
    private val repository: Repository
) : ViewModel() {
    private val _uiState = MutableStateFlow(CulturalActivityUiState())
    val uiState: StateFlow<CulturalActivityUiState> get() = _uiState

    init {
        culturalActivityId?.let {
            viewModelScope.launch {
                val culturalActivity = repository.getCulturalActivity(culturalActivityId)
                _uiState.value = CulturalActivityUiState(culturalActivity)
            }
        }
    }

    fun saveCulturalActivity() {
        viewModelScope.launch {
            repository.putCulturalActivity(uiState.value.culturalActivity)
        }
    }

    fun deleteCulturalActivity() {
        viewModelScope.launch {
            culturalActivityId?.let { repository.deleteCulturalActivity(it) }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(culturalActivityId: Int?): CulturalActivityInfoViewModel
    }
}