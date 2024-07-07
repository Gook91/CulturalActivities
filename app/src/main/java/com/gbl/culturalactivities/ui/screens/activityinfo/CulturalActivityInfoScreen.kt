package com.gbl.culturalactivities.ui.screens.activityinfo

import android.icu.text.DateFormat
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gbl.culturalactivities.R
import com.gbl.culturalactivities.entity.CulturalActivity
import java.util.Calendar

@Composable
fun CulturalActivityInfoScreen(
    id: Int? = null,
    viewModel: CulturalActivityInfoViewModel
) {
    Scaffold(
        floatingActionButton = {
            FloatingButtonSave {
                viewModel.saveCulturalActivity()
            }
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            CulturalActivityInfo(viewModel = viewModel)
        }
    }
}

@Composable
private fun FloatingButtonSave(saveCulturalActivity: () -> Unit) {
    FloatingActionButton(onClick = saveCulturalActivity) {
        Icon(
            painter = painterResource(id = R.drawable.save_icon),
            contentDescription = stringResource(id = R.string.save_button)
        )
    }
}

@Composable
private fun CulturalActivityInfo(viewModel: CulturalActivityInfoViewModel) {
    Column {
        TextField(
            value = viewModel.nameState,
            onValueChange = {
                viewModel.nameState = it
            },
            label = { Text(text = stringResource(id = R.string.name_title)) }
        )
        TextField(
            value = viewModel.descriptionState,
            onValueChange = { viewModel.descriptionState = it },
            label = { Text(text = stringResource(id = R.string.description_title)) }
        )
        TextField(
            value = viewModel.placeState,
            onValueChange = { viewModel.placeState = it },
            label = { Text(text = stringResource(id = R.string.place_title)) }
        )
        TextField(
            value = viewModel.linkState,
            onValueChange = { viewModel.linkState = it },
            label = { Text(text = stringResource(id = R.string.link_title)) },
            singleLine = true
        )
        CalendarText(
            label = stringResource(id = R.string.ending_date_title),
            dateInMillis = viewModel.endingDateState
        )

        CalendarText(
            label = stringResource(id = R.string.date_of_visit_title),
            dateInMillis = viewModel.dateOfVisitState
        )
    }

}

@Composable
private fun CalendarText(label: String = "", dateInMillis: Long? = null) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label)

        val dateInText = if (dateInMillis == null)
            ""
        else {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = dateInMillis
            DateFormat.getDateInstance().format(calendar.time)
        }
        Text(text = dateInText)
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.open_calendar_icon),
                contentDescription = stringResource(id = R.string.select_date_button)
            )
        }
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.delete_icon),
                contentDescription = stringResource(id = R.string.clear_date_button)
            )
        }
    }
}

@Preview
@Composable
fun PreviewCulturalActivityInfo() {
    val culturalActivity = object : CulturalActivity() {
        override val id = 0
        override val name = "ARS VIVENDI"
        override val description = "Франс Снейдерс и фламандский натюрморт XVII века"
        override val place = "Эрмитаж"
        override val link =
            "https://hermitagemuseum.org/wps/portal/hermitage/what-s-on/temp_exh/2024/barocco/!ut/p/z1/pVJNU8IwFPwtHnrs5DX9SDnG4ljATkEppbkwaQ0ljm2hRMV_b-rAweFr1JySzO57-_YtYmiOWM3fZcmVbGr-qt8Z8xYxpZ5lBzCMByMCNLaSuwEZ4PGAoPQbAGcOBcR-8H3qA713x1GUPOB4ZO_5FwDscv8ZYoi91iXK2rfuui7kM8qElS891y_MghMwHQK26XPhmdjmPY6fc-LYpEMXtVqrFcpWoq2k4qUwoGhqJWplgNitZC47H7YGKFGtm5a3nwZgwI4BOW-bomiO9B8JZJftSTsZJypM3TCOZwEOnD3gQo1MayCLB7C8sOdYQ98N-0DxbBSEDrGgh1H6LsUHSuqmrfROn37pU3jocG7KCZzqkAbRYpLcPWru3_ZzZSzrn2MNr0VLZ1--bDaM6px0odgpNP97UHQ13EZBVGqRXK1MWS_1Z4dB8wNmXSVJ5duVOYcXt6wW_dtI5P7HdFml_pbe3HwBaNI6Gg!!/dz/d5/L2dBISEvZ0FBIS9nQSEh/?lng=ru"
        override val endingDate = 1725753600000L
        override val dateOfVisit = 1721088000000L
    }
    //CulturalActivityInfo(culturalActivity = culturalActivity)
}