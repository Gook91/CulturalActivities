package com.gbl.culturalactivities.ui.screens

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination.Companion.createRoute
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.serialization.generateHashCode
import androidx.navigation.serialization.generateRouteWithArgs
import androidx.navigation.toRoute
import com.gbl.culturalactivities.ui.screens.activitieslist.CulturalActivitiesListScreen
import com.gbl.culturalactivities.ui.screens.activitieslist.CulturalActivitiesListViewModel
import com.gbl.culturalactivities.ui.screens.activityinfo.CulturalActivityInfoScreen
import com.gbl.culturalactivities.ui.screens.activityinfo.CulturalActivityInfoViewModel
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer

@Serializable
object ListScreen

@Serializable
data class InfoScreen(val id: Int)

@Serializable
object AddNewScreen

fun NavGraphBuilder.listScreenDestination(
    onNavigateToInfoScreen: (id: Int) -> Unit,
    onNavigateToNewCulturalActivity: () -> Unit
) {
    composable<ListScreen> {
        val viewModel: CulturalActivitiesListViewModel = hiltViewModel()
        val listState = viewModel.listFlow.collectAsStateWithLifecycle()
        CulturalActivitiesListScreen(
            listState = listState,
            onNavigateToIdCulturalActivity = onNavigateToInfoScreen,
            onNavigateToNewCulturalActivity = onNavigateToNewCulturalActivity
        )
    }
}

fun NavGraphBuilder.infoScreenDestination() {
    composable<InfoScreen> { backStackEntry ->
        val id: Int = backStackEntry.toRoute<InfoScreen>().id
        val viewModel = getInfoViewModel(id)
        CulturalActivityInfoScreen(
            id = id,
            viewModel = viewModel
        )
    }
}

fun NavGraphBuilder.addNewScreenDestination() {
    composable<AddNewScreen> {
        val viewModel = getInfoViewModel()
        CulturalActivityInfoScreen(viewModel = viewModel)
    }
}

@Composable
private fun getInfoViewModel(id: Int? = null): CulturalActivityInfoViewModel =
    hiltViewModel<CulturalActivityInfoViewModel, CulturalActivityInfoViewModel.Factory>(
        creationCallback = { factory -> factory.create(id) }
    )

fun NavController.navigateToInfoScreenById(id: Int) {
    navigate(route = InfoScreen(id = id))
}

fun NavController.navigateToAddNewScreen() {
    navigate(route = AddNewScreen)
}