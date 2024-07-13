package com.gbl.culturalactivities.ui.screens.activitieslist

import android.text.format.DateFormat
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.gbl.culturalactivities.R
import com.gbl.culturalactivities.entity.CulturalActivity
import com.gbl.culturalactivities.ui.SingleItemPreviewParameterProvider
import java.util.Calendar
import java.util.Locale

@Composable
fun CulturalActivityItem(
    culturalActivity: CulturalActivity,
    onItemClick: () -> Unit
) {
    Card(
        onClick = onItemClick,
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.borders))
    ) {
        Row(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.borders)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val calendar = Calendar.getInstance()

            val dayString: String
            val monthString: String
            if (culturalActivity.dateOfVisit == null) {
                dayString = stringResource(id = R.string.no_date_day)
                monthString = stringResource(id = R.string.no_date_month)
            } else {
                calendar.timeInMillis = culturalActivity.dateOfVisit ?: 0
                dayString = calendar.get(Calendar.DAY_OF_MONTH).toString()
                monthString =
                    calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault())
                        ?: ""
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(40.dp)
            ) {
                Text(text = dayString)
                Text(text = monthString)
            }

            Column(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.borders))
                    .weight(1f)
            ) {
                Text(text = culturalActivity.name)
                Text(text = culturalActivity.place)
            }
            culturalActivity.endingDate?.let {
                calendar.timeInMillis = culturalActivity.endingDate ?: 0
                val endingDateFormat = DateFormat.getDateFormat(LocalContext.current)
                val endingDateString = endingDateFormat.format(calendar.timeInMillis)
                Text(
                    text = stringResource(id = R.string.before_ending_date, endingDateString)
                )
            }

        }
    }
}

@Preview
@Composable
fun PreviewCulturalActivityItem(
    @PreviewParameter(SingleItemPreviewParameterProvider::class)
    culturalActivity: CulturalActivity
) {
    CulturalActivityItem(culturalActivity = culturalActivity) {}
}