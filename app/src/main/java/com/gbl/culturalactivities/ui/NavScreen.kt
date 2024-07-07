package com.gbl.culturalactivities.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gbl.culturalactivities.ui.screens.ListScreen
import com.gbl.culturalactivities.ui.screens.activitieslist.CulturalActivitiesListScreen
import com.gbl.culturalactivities.ui.screens.addNewScreenDestination
import com.gbl.culturalactivities.ui.screens.infoScreenDestination
import com.gbl.culturalactivities.ui.screens.listScreenDestination
import com.gbl.culturalactivities.ui.screens.navigateToAddNewScreen
import com.gbl.culturalactivities.ui.screens.navigateToInfoScreenById

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
            })
        infoScreenDestination()
        addNewScreenDestination()
    }
}