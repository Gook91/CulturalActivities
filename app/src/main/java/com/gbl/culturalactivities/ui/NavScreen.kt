package com.gbl.culturalactivities.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.gbl.culturalactivities.ui.screens.ListScreen
import com.gbl.culturalactivities.ui.screens.addNewScreenDestination
import com.gbl.culturalactivities.ui.screens.infoScreenDestination
import com.gbl.culturalactivities.ui.screens.listScreenDestination
import com.gbl.culturalactivities.ui.screens.navigateToAddNewScreen
import com.gbl.culturalactivities.ui.screens.navigateToInfoScreenById
import com.gbl.culturalactivities.ui.screens.navigateToSettingsScreen
import com.gbl.culturalactivities.ui.screens.settingScreenDestination

@Composable
fun NavScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ListScreen) {
        listScreenDestination(
            onNavigateToInfoScreen = { id ->
                navController.navigateToInfoScreenById(id = id)
            },
            onNavigateToNewCulturalActivity = {
                navController.navigateToAddNewScreen()
            },
            onNavigateToSettingsScreen = {
                navController.navigateToSettingsScreen()
            })
        infoScreenDestination(
            onNavigateToPreviousScreen = { navController.popBackStack() }
        )
        addNewScreenDestination(
            onNavigateToPreviousScreen = { navController.popBackStack() }
        )
        settingScreenDestination(
            onNavigateToPreviousScreen = { navController.popBackStack() }
        )
    }
}