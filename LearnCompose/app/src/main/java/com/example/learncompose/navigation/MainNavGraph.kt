package com.example.learncompose.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.learncompose.ui.bottomNavigation.BottomNavigationScreen
import com.example.learncompose.ui.bottomsheet.BottomSheetScreen
import com.example.learncompose.ui.home.HomeScreen
import com.example.learncompose.ui.lazy_column.LazyColumnHomeScreen
import com.example.learncompose.ui.textfield.TextFieldHomeScreen

fun NavGraphBuilder.mainNavGraph(navController: NavController) {
    navigation(
        startDestination = NavigationRoute.HomeRoute.HomeL1Route.route,
        route = NavigationRoute.HomeRoute.route
    ) {
        composable(NavigationRoute.HomeRoute.HomeL1Route.route) {
           // HomeScreen(navController)
            BottomNavigationScreen()
        }

        composable(NavigationRoute.ComponentRoute.route) {
            TextFieldHomeScreen(navController)
        }

        composable(NavigationRoute.ComponentRoute.BottomNavigationRoute.route) {
            BottomNavigationScreen()
        }

        composable(NavigationRoute.ComponentRoute.LazyColumnRoute.route) {
            LazyColumnHomeScreen()
        }

    }
}