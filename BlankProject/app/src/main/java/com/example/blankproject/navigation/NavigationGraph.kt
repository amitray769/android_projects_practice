package com.example.blankproject.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.blankproject.ui.planetDetails.PlanetDetailsScreen
import com.example.blankproject.ui.planetDetails.PlanetListScreen

fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
    navigation(
        startDestination = "home/list",
        route = "home"
    ) {
        composable(
            route = "home/list"
        ) {
            PlanetListScreen(navController)
        }

        composable(
            route = "home/details/{planetUrl}"
        ) {
            val planetUrl = it.arguments?.getString("planetUrl")
            PlanetDetailsScreen(navController, planetUrl)
        }

    }

}