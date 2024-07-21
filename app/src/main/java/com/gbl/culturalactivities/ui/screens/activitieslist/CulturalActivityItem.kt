package com.gbl.culturalactivities.ui.screens.activitieslist

import android.text.format.DateFormat
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.gbl.culturalactivities.R
import com.gbl.culturalactivities.entity.CulturalActivity
import com.gbl.culturalactivities.ui.SingleItemPreviewParameterProvider
import com.gbl.culturalactivities.ui.views.DateIcon
import java.util.Calendar

@Composable
fun CulturalActivityItem(
    culturalActivity: CulturalActivity,
    onItemClick: () -> Unit
) {
    Card(
        onClick = onItemClick
    ) {
        Row(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.view_padding)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DateIcon(dateInMillis = culturalActivity.dateOfVisit, borderColor = Color.Transparent)
            MainInfo(modifier = Modifier.weight(1f), culturalActivity = culturalActivity)
            culturalActivity.endingDate?.let { EndingDateText(endingDate = it) }
        }
    }
}

@Composable
private fun MainInfo(
    modifier: Modifier,
    culturalActivity: CulturalActivity
) {
    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.view_padding))
    ) {
        Text(text = culturalActivity.name, style = MaterialTheme.typography.bodyLarge)
        Text(text = culturalActivity.place, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
private fun EndingDateText(endingDate: Long) {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = endingDate
    val endingDateFormat = DateFormat.getDateFormat(LocalContext.current)
    val endingDateString = endingDateFormat.format(calendar.timeInMillis)
    Text(
        text = stringResource(id = R.string.before_ending_date, endingDateString),
        style = MaterialTheme.typography.bodyMedium
    )
}

@Preview
@Composable
fun PreviewCulturalActivityItem(
    @PreviewParameter(SingleItemPreviewParameterProvider::class)
    culturalActivity: CulturalActivity
) {
    CulturalActivityItem(culturalActivity = culturalActivity) {}
}