package com.gbl.culturalactivities.ui.screens.activitieslist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.gbl.culturalactivities.entity.CulturalActivity
import com.gbl.culturalactivities.ui.ListPreviewParameterProvider

@Composable
fun CulturalActivitiesList(
    culturalActivitiesList: List<CulturalActivity>,
    onItemClick: (Int) -> Unit,
) {
    LazyColumn {
        items(culturalActivitiesList) { culturalActivity ->
            CulturalActivityItem(culturalActivity = culturalActivity) {
                culturalActivity.id?.let { onItemClick(it) }
            }
        }
    }
}

@Preview
@Composable
fun PreviewCulturalActivitiesList(
    @PreviewParameter(ListPreviewParameterProvider::class) dataList: List<CulturalActivity>
) {
    CulturalActivitiesList(dataList) {}
}