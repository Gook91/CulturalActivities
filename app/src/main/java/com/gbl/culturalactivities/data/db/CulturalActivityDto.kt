package com.gbl.culturalactivities.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gbl.culturalactivities.domain.entity.CulturalActivity

@Entity(tableName = "activities")
data class CulturalActivityDto(
    @PrimaryKey(autoGenerate = true) override val id: Int? = null,
    override val name: String,
    override val description: String,
    override val place: String,
    override val link: String,
    override val endingDate: Long?,
    override val dateOfVisit: Long?,
) : CulturalActivity() {
    constructor(culturalActivity: CulturalActivity) : this(
        id = culturalActivity.id,
        name = culturalActivity.name,
        description = culturalActivity.description,
        place = culturalActivity.place,
        link = culturalActivity.link,
        endingDate = culturalActivity.endingDate,
        dateOfVisit = culturalActivity.dateOfVisit
    )
}
