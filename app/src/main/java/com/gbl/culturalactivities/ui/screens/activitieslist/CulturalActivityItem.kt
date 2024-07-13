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
import androidx.compose.ui.unit.dp
import com.gbl.culturalactivities.R
import com.gbl.culturalactivities.entity.CulturalActivity
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
fun PreviewCulturalActivityItem() {
    val someActivity = object : CulturalActivity() {
        override val id = 0
        override val name = "ARS VIVENDI"
        override val description = "Франс Снейдерс и фламандский натюрморт XVII века"
        override val place = "Эрмитаж"
        override val link =
            "https://hermitagemuseum.org/wps/portal/hermitage/what-s-on/temp_exh/2024/barocco/!ut/p/z1/pVJNU8IwFPwtHnrs5DX9SDnG4ljATkEppbkwaQ0ljm2hRMV_b-rAweFr1JySzO57-_YtYmiOWM3fZcmVbGr-qt8Z8xYxpZ5lBzCMByMCNLaSuwEZ4PGAoPQbAGcOBcR-8H3qA713x1GUPOB4ZO_5FwDscv8ZYoi91iXK2rfuui7kM8qElS891y_MghMwHQK26XPhmdjmPY6fc-LYpEMXtVqrFcpWoq2k4qUwoGhqJWplgNitZC47H7YGKFGtm5a3nwZgwI4BOW-bomiO9B8JZJftSTsZJypM3TCOZwEOnD3gQo1MayCLB7C8sOdYQ98N-0DxbBSEDrGgh1H6LsUHSuqmrfROn37pU3jocG7KCZzqkAbRYpLcPWru3_ZzZSzrn2MNr0VLZ1--bDaM6px0odgpNP97UHQ13EZBVGqRXK1MWS_1Z4dB8wNmXSVJ5duVOYcXt6wW_dtI5P7HdFml_pbe3HwBaNI6Gg!!/dz/d5/L2dBISEvZ0FBIS9nQSEh/?lng=ru"
        override val endingDate = 1725753600000L
        override val dateOfVisit: Long? = 1721088000000L
    }

    CulturalActivityItem(culturalActivity = someActivity) {}
}