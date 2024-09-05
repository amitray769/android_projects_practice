package com.example.learncompose.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learncompose.navigation.NavigationRoute
import com.example.learncompose.ui.atom.LCButton

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LCButton(buttonText = "TextField") {
            navController.navigate(NavigationRoute.ComponentRoute.route)
        }

        LCButton(buttonText = "LazyColumn") {
            navController.navigate(NavigationRoute.ComponentRoute.LazyColumnRoute.route)
        }


    }
}