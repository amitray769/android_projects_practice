package com.example.learncompose.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.learncompose.navigation.NavigationRoute

@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {

        Button(
            onClick = { navController.navigate(NavigationRoute.ComponentRoute.route) }) {
            Text(
                text = "Show TextFields"
            )
        }


    }
}