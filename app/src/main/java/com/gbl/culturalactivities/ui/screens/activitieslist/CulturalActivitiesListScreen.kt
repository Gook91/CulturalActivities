package com.gbl.culturalactivities.ui.screens.activitieslist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.gbl.culturalactivities.R
import com.gbl.culturalactivities.domain.entity.CulturalActivity
import com.gbl.culturalactivities.ui.ListPreviewParameterProvider

@Composable
fun CulturalActivitiesListScreen(
    dataList: List<CulturalActivity>,
    onNavigateToIdCulturalActivity: (Int) -> Unit,
    onNavigateToNewCulturalActivity: () -> Unit,
    onNavigateToSettingsScreen: () -> Unit,
) {
    Scaffold(
        topBar = { ListTopBar(onNavigateToSettingsScreen) },
        floatingActionButton = { FloatingButtonAdd(onNavigateToNewCulturalActivity) }
    ) { innerPaddings ->
        Box(
            modifier = Modifier
                .padding(paddingValues = innerPaddings)
                .padding(horizontal = dimensionResource(id = R.dimen.screen_padding))
        ) {
            CulturalActivitiesList(
                culturalActivitiesList = dataList,
                onItemClick = onNavigateToIdCulturalActivity
            )
        }
    }
}

@Composable
private fun FloatingButtonAdd(onNavigateToNewCulturalActivity: () -> Unit) {
    FloatingActionButton(onClick = onNavigateToNewCulturalActivity) {
        Icon(
            painter = painterResource(id = R.drawable.add_icon),
            contentDescription = stringResource(id = R.string.add_new_button)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListTopBar(
    onNavigateToSettingsScreen: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        actions = {
            IconButton(onClick = onNavigateToSettingsScreen) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = stringResource(id = R.string.settings)
                )
            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCulturalActivitiesListScreen(
    @PreviewParameter(ListPreviewParameterProvider::class)
    dataList: List<CulturalActivity>
) {
    CulturalActivitiesListScreen(
        dataList = dataList,
        onNavigateToIdCulturalActivity = {},
        onNavigateToNewCulturalActivity = {},
        onNavigateToSettingsScreen = {},
    )
}