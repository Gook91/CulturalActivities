package com.gbl.culturalactivities.ui.screens.activitieslist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.gbl.culturalactivities.R
import com.gbl.culturalactivities.entity.CulturalActivity

@Composable
fun CulturalActivitiesListScreen(
    listState: State<List<CulturalActivity>>,
    onNavigateToIdCulturalActivity: (Int) -> Unit,
    onNavigateToNewCulturalActivity: () -> Unit
) {
    Scaffold(
        floatingActionButton = { FloatingButtonAdd(onNavigateToNewCulturalActivity) }
    ) { innerPaddings ->
        Box(modifier = Modifier.padding(innerPaddings)) {
            val culturalActivitiesList = listState.value
            CulturalActivitiesList(
                culturalActivitiesList = culturalActivitiesList,
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