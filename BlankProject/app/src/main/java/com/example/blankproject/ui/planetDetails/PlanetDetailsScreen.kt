package com.example.blankproject.ui.planetDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.blankproject.data.models.Result
import com.example.blankproject.ui.viewmodels.PlanetDetailsScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetDetailsScreen(navController: NavController, planetUrl: String?, planetDetailsScreenViewModel: PlanetDetailsScreenViewModel = viewModel()) {
    val state = planetDetailsScreenViewModel.state

    LaunchedEffect(Unit) {
        planetDetailsScreenViewModel.getPlanetDetailsByPage(planetUrl!!)
    }
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Planet List")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                        }

                        PlanetDetailsItem(state)
                    }
                )
            },

        ) { innerPadding ->
            // Main content
            Column(modifier = Modifier.padding(innerPadding)) {

              // PlanetDetailsItem()
            }
        }
    }

@Composable
fun PlanetDetailsItem(modifier: Modifier = Modifier, planet: Result) {
    Column(modifier = modifier) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.padding(vertical = 16.dp).fillMaxWidth(),
            // colors = CardColors.Default.copy()
        ) {
            Text(planet.name, modifier = Modifier.padding(vertical = 8.dp))
            Text(planet.population, modifier = Modifier.padding(vertical = 8.dp))
            Text(planet.diameter, modifier = Modifier.padding(vertical = 8.dp))
        }

    }
}