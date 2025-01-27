package com.example.kotlinflowlearnbyexample.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.kotlinflowlearnbyexample.ui.HomeScreen

fun NavGraphBuilder.mainNavGraph(navController: NavController) {
    navigation(
        route = "home",
        startDestination = "home/main"
    ){
        composable("home/main"){
             HomeScreen(navController)
        }

    }

}