package com.example.kotlinflowlearnbyexample.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

fun NavGraphBuilder.mainNavGraph() {
    navigation(
        route = "home",
        startDestination = "home/main"
    ){
        composable("home/main"){
            // HomeScreen()
        }

    }

}