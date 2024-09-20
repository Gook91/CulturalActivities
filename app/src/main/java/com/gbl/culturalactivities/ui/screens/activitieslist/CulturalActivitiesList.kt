package com.gbl.culturalactivities.ui.screens.activitieslist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.gbl.culturalactivities.R
import com.gbl.culturalactivities.domain.entity.CulturalActivity
import com.gbl.culturalactivities.ui.ListPreviewParameterProvider
import java.util.Calendar
import kotlin.math.max

@Composable
fun CulturalActivitiesList(
    culturalActivitiesList: List<CulturalActivity>,
    onItemClick: (Int) -> Unit,
) {
    if (culturalActivitiesList.isNotEmpty()) {
        val initialFirstPosition = getInitialFirstPosition(culturalActivitiesList)
        val scrollState = rememberLazyListState(initialFirstVisibleItemIndex = initialFirstPosition)
        LazyColumn(
            contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.screen_padding)),
            verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.view_padding)),
            state = scrollState,
        ) {
            items(culturalActivitiesList) { culturalActivity ->
                CulturalActivityItem(culturalActivity = culturalActivity) {
                    culturalActivity.id?.let { onItemClick(it) }
                }
            }
        }
    } else {
        EmptyListPlaceholder()
    }
}

private fun getInitialFirstPosition(culturalActivitiesList: List<CulturalActivity>): Int {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, -1)
    val yesterdayDate = calendar.timeInMillis
    val initialPosition =
        culturalActivitiesList.indexOfFirst { culturalActivity ->
            if (culturalActivity.dateOfVisit == null) true
            else (culturalActivity.dateOfVisit ?: 0) > yesterdayDate
        }
    // If indexOfFirst didn't find anything and returns -1, we check it and return 0 as the starting position.
    // If ignore this check - app will crashing, since initial position can be only be 0 or a positive value
    return max(initialPosition, 0)
}

@Composable
private fun EmptyListPlaceholder() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.list_is_empty),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview
@Composable
fun PreviewCulturalActivitiesList(
    @PreviewParameter(ListPreviewParameterProvider::class) dataList: List<CulturalActivity>
) {
    CulturalActivitiesList(dataList) {}
}