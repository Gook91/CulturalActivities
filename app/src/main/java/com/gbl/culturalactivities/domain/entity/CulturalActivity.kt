package com.gbl.culturalactivities.domain.entity

abstract class CulturalActivity {
    abstract val id: Int?
    abstract val name: String
    abstract val description: String
    abstract val place: String
    abstract val link: String
    abstract val endingDate: Long?
    abstract val dateOfVisit: Long?
}