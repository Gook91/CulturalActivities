package com.gbl.culturalactivities.ui.screens.activityinfo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.gbl.culturalactivities.R
import com.gbl.culturalactivities.entity.CulturalActivity

@Composable
fun CulturalActivityInfoScreen(
    culturalActivityUiState: CulturalActivityUiState,
    saveFunction: () -> Unit,
    deleteFunction: () -> Unit,
    onNavigateToPreviousScreen: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                onNavigateToPreviousScreen = onNavigateToPreviousScreen,
                deleteCulturalActivity = {
                    deleteFunction()
                    onNavigateToPreviousScreen()
                }
            )
        },
        floatingActionButton = {
            FloatingButtonSave {
                saveFunction()
                onNavigateToPreviousScreen()
            }
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            CulturalActivityEditView(culturalActivityUiState)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    onNavigateToPreviousScreen: () -> Unit,
    deleteCulturalActivity: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onNavigateToPreviousScreen) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_navigation)
                )
            }
        },
        title = {},
        actions = {
            IconButton(onClick = deleteCulturalActivity) {
                Icon(
                    painter = painterResource(id = R.drawable.delete_icon),
                    contentDescription = stringResource(id = R.string.delete_button)
                )
            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCulturalActivityInfoScreen() {
    CulturalActivityInfoScreen(
        culturalActivityUiState = CulturalActivityUiState(
            object : CulturalActivity() {
                override val id = 0
                override val name = "ARS VIVENDI"
                override val description = "Франс Снейдерс и фламандский натюрморт XVII века"
                override val place = "Эрмитаж"
                override val link =
                    "https://hermitagemuseum.org/wps/portal/hermitage/what-s-on/temp_exh/2024/barocco/!ut/p/z1/pVJNU8IwFPwtHnrs5DX9SDnG4ljATkEppbkwaQ0ljm2hRMV_b-rAweFr1JySzO57-_YtYmiOWM3fZcmVbGr-qt8Z8xYxpZ5lBzCMByMCNLaSuwEZ4PGAoPQbAGcOBcR-8H3qA713x1GUPOB4ZO_5FwDscv8ZYoi91iXK2rfuui7kM8qElS891y_MghMwHQK26XPhmdjmPY6fc-LYpEMXtVqrFcpWoq2k4qUwoGhqJWplgNitZC47H7YGKFGtm5a3nwZgwI4BOW-bomiO9B8JZJftSTsZJypM3TCOZwEOnD3gQo1MayCLB7C8sOdYQ98N-0DxbBSEDrGgh1H6LsUHSuqmrfROn37pU3jocG7KCZzqkAbRYpLcPWru3_ZzZSzrn2MNr0VLZ1--bDaM6px0odgpNP97UHQ13EZBVGqRXK1MWS_1Z4dB8wNmXSVJ5duVOYcXt6wW_dtI5P7HdFml_pbe3HwBaNI6Gg!!/dz/d5/L2dBISEvZ0FBIS9nQSEh/?lng=ru"
                override val endingDate = 1725753600000L
                override val dateOfVisit = 1721088000000L
            }
        ),
        saveFunction = {},
        deleteFunction = {},
        onNavigateToPreviousScreen = {}
    )
}