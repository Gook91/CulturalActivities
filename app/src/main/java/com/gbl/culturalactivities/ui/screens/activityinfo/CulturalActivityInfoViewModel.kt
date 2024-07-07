package com.gbl.culturalactivities.ui.screens.activityinfo

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbl.culturalactivities.data.Repository
import com.gbl.culturalactivities.entity.CulturalActivity
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@HiltViewModel(assistedFactory = CulturalActivityInfoViewModel.Factory::class)
class CulturalActivityInfoViewModel @AssistedInject constructor(
    //savedStateHandle: SavedStateHandle,
    @Assisted private val culturalActivityId: Int? = null,
    private val repository: Repository
) : ViewModel() {
    //private val culturalActivityId: Int? = savedStateHandle["culturalActivityId"]
    var nameState: String by mutableStateOf("")
    var descriptionState: String by mutableStateOf("")
    var placeState: String by mutableStateOf("")
    var linkState: String by mutableStateOf("")
    var endingDateState: Long? by mutableStateOf(null)
    var dateOfVisitState: Long? by mutableStateOf(null)

    init {
        culturalActivityId?.let { id ->
            viewModelScope.launch {
                val culturalActivity = repository.getCulturalActivity(id)
                nameState = culturalActivity.name
                descriptionState = culturalActivity.description
                placeState = culturalActivity.place
                linkState = culturalActivity.link
                endingDateState = culturalActivity.endingDate
                dateOfVisitState = culturalActivity.dateOfVisit
            }
        }
    }

    fun saveCulturalActivity() {
        viewModelScope.launch {
            val newCulturalActivity = object : CulturalActivity(){
                override val id: Int? = culturalActivityId
                override val name: String = nameState
                override val description: String = descriptionState
                override val place: String = placeState
                override val link: String = linkState
                override val endingDate: Long? = endingDateState
                override val dateOfVisit: Long? = dateOfVisitState
            }
            repository.putCulturalActivity(newCulturalActivity)
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