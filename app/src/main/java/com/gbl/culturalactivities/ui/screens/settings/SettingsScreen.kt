package com.gbl.culturalactivities.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gbl.culturalactivities.R

@Composable
fun SettingsScreen(
    onNavigateToPreviousScreen: () -> Unit
) {
    Scaffold(
        topBar = { SettingsTopBar(onNavigateToPreviousScreen) }
    ) { innerPaddings ->
        Column(modifier = Modifier.padding(innerPaddings)) {

            CalendarSyncModule()

            BackupModule()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsTopBar(
    onNavigateToPreviousScreen: () -> Unit
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
        title = { Text(text = stringResource(id = R.string.settings)) }
    )
}

@Composable
private fun CalendarSyncModule() {

}

@Composable
private fun BackupModule() {

}