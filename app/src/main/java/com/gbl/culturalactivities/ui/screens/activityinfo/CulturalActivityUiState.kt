package com.gbl.culturalactivities.ui.screens.activityinfo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.gbl.culturalactivities.domain.entity.CulturalActivity

class CulturalActivityUiState(
    culturalActivity: CulturalActivity? = null
) {
    private val culturalActivityId: Int? = culturalActivity?.id
    var nameState: String by mutableStateOf(culturalActivity?.name ?: "")
    var descriptionState: String by mutableStateOf(culturalActivity?.description ?: "")
    var placeState: String by mutableStateOf(culturalActivity?.place ?: "")
    var linkState: String by mutableStateOf(culturalActivity?.link ?: "")
    var endingDateState: Long? by mutableStateOf(culturalActivity?.endingDate)
    var dateOfVisitState: Long? by mutableStateOf(culturalActivity?.dateOfVisit)

    val culturalActivity: CulturalActivity
        get() = object : CulturalActivity() {
            override val id: Int? = culturalActivityId
            override val name: String = nameState
            override val description: String = descriptionState
            override val place: String = placeState
            override val link: String = linkState
            override val endingDate: Long? = endingDateState
            override val dateOfVisit: Long? = dateOfVisitState
        }
}