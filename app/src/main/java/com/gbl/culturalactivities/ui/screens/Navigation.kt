package com.gbl.culturalactivities.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.gbl.culturalactivities.ui.screens.activitieslist.CulturalActivitiesListScreen
import com.gbl.culturalactivities.ui.screens.activitieslist.CulturalActivitiesListViewModel
import com.gbl.culturalactivities.ui.screens.activityinfo.CulturalActivityInfoScreen
import com.gbl.culturalactivities.ui.screens.activityinfo.CulturalActivityInfoViewModel
import kotlinx.serialization.Serializable

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
            dataList = listState.value,
            onNavigateToIdCulturalActivity = onNavigateToInfoScreen,
            onNavigateToNewCulturalActivity = onNavigateToNewCulturalActivity
        )
    }
}

fun NavGraphBuilder.infoScreenDestination(
    onNavigateToPreviousScreen: () -> Unit
) {
    composable<InfoScreen> { backStackEntry ->
        val id: Int = backStackEntry.toRoute<InfoScreen>().id
        InfoScreen(
            id = id,
            onNavigateToPreviousScreen = onNavigateToPreviousScreen
        )
    }
}

// Serializable class in SafeArg navigation cannot have a nullable value, so this function was created
fun NavGraphBuilder.addNewScreenDestination(
    onNavigateToPreviousScreen: () -> Unit
) {
    composable<AddNewScreen> {
        InfoScreen(onNavigateToPreviousScreen = onNavigateToPreviousScreen)
    }
}

@Composable
private fun InfoScreen(
    id: Int? = null,
    onNavigateToPreviousScreen: () -> Unit
) {
    val viewModel =
        hiltViewModel<CulturalActivityInfoViewModel, CulturalActivityInfoViewModel.Factory>(
            creationCallback = { factory -> factory.create(id) }
        )
    val culturalActivityUiState = viewModel.uiState.collectAsState()
    CulturalActivityInfoScreen(
        culturalActivityUiState = culturalActivityUiState.value,
        saveFunction = { viewModel.saveCulturalActivity() },
        deleteFunction = { viewModel.deleteCulturalActivity() },
        onNavigateToPreviousScreen = onNavigateToPreviousScreen
    )
}

fun NavController.navigateToInfoScreenById(id: Int) {
    navigate(route = InfoScreen(id = id))
}

fun NavController.navigateToAddNewScreen() {
    navigate(route = AddNewScreen)
}