package com.gbl.culturalactivities.ui.screens.activityinfo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.gbl.culturalactivities.R
import com.gbl.culturalactivities.entity.CulturalActivity
import com.gbl.culturalactivities.ui.SingleItemPreviewParameterProvider

@Composable
fun CulturalActivityInfoScreen(
    culturalActivityUiState: CulturalActivityUiState,
    saveFunction: () -> Unit,
    deleteFunction: () -> Unit,
    onNavigateToPreviousScreen: () -> Unit
) {
    Scaffold(
        modifier = Modifier.imePadding(),
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
        },

        ) { contentPadding ->
        CulturalActivityEditView(
            culturalActivityUiState = culturalActivityUiState,
            modifier = Modifier
                .padding(contentPadding)
                .padding(horizontal = dimensionResource(id = R.dimen.screen_padding))
                .fillMaxSize()
        )
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
fun PreviewCulturalActivityInfoScreen(
    @PreviewParameter(SingleItemPreviewParameterProvider::class, limit = 1)
    culturalActivity: CulturalActivity
) {
    CulturalActivityInfoScreen(
        culturalActivityUiState = CulturalActivityUiState(culturalActivity),
        saveFunction = {},
        deleteFunction = {},
        onNavigateToPreviousScreen = {}
    )
}