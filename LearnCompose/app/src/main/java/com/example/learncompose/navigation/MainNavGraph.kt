package com.example.learncompose.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.learncompose.ui.bottomNavigation.BottomNavigationScreen
import com.example.learncompose.ui.bottomsheet.BottomSheetScreen
import com.example.learncompose.ui.home.HomeScreen

fun NavGraphBuilder.mainNavGraph(navController: NavController) {
    navigation(
        startDestination = NavigationRoute.HomeRoute.HomeL1Route.route,
        route = NavigationRoute.HomeRoute.route
    ) {
        composable(NavigationRoute.HomeRoute.HomeL1Route.route){
           HomeScreen(navController)
        }

        composable(NavigationRoute.ComponentRoute.route){
           // TextFieldHomeScreen(navController)
            BottomSheetScreen(navController)

        }
        composable(NavigationRoute.ComponentRoute.route){
           // TextFieldHomeScreen(navController)
           // BottomSheetScreen(navController)
            BottomNavigationScreen()


        }

    }
}