package com.gbl.culturalactivities.ui.screens.activityinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbl.culturalactivities.domain.usecase.culturalactivity.DeleteCulturalActivityUseCase
import com.gbl.culturalactivities.domain.usecase.culturalactivity.GetCulturalActivityUseCase
import com.gbl.culturalactivities.domain.usecase.culturalactivity.PutCulturalActivityUseCase
import com.gbl.culturalactivities.domain.usecase.place.GetPlacesUseCase
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
    getCulturalActivityUseCase: GetCulturalActivityUseCase,
    private val putCulturalActivityUseCase: PutCulturalActivityUseCase,
    private val deleteCulturalActivityUseCase: DeleteCulturalActivityUseCase,
    private val getPlacesUseCase: GetPlacesUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(CulturalActivityUiState())
    val uiState: StateFlow<CulturalActivityUiState> get() = _uiState

    init {
        viewModelScope.launch {
            val culturalActivityDeferred = async {
                culturalActivityId?.let { getCulturalActivityUseCase(it) }
            }
            val placesDeferred = async {
                getPlacesUseCase()
            }
            val culturalActivity = culturalActivityDeferred.await()
            val places = placesDeferred.await()
            _uiState.value = CulturalActivityUiState(culturalActivity, places)
        }
    }

    fun saveCulturalActivity() {
        viewModelScope.launch {
            putCulturalActivityUseCase(uiState.value.culturalActivity)
        }
    }

    fun deleteCulturalActivity() {
        viewModelScope.launch {
            culturalActivityId?.let { deleteCulturalActivityUseCase(it) }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(culturalActivityId: Int?): CulturalActivityInfoViewModel
    }
}