package com.example.blankproject.ui.planetDetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.blankproject.data.models.Result
import com.example.blankproject.ui.viewmodels.PlanetDetailsScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetListScreen(
    navController: NavController,
    planetDetailsScreenViewModel: PlanetDetailsScreenViewModel = viewModel()
) {

    LaunchedEffect(Unit) {
        planetDetailsScreenViewModel.getPlanetDetails()
    }

    val planetDetails = planetDetailsScreenViewModel.planetPagingFlow.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "")
                },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back action */ }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
    ) { innerPadding ->
        // Main content
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
          val planetDetails =   planetDetails.itemSnapshotList.items
            items(planetDetails.size) { planet ->
                PlanetItem(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                    planet = planetDetails[planet],
                    onPlanetClick = {
                        navController.navigate("home/details/${it.url}")
                    }
                )
            }

        }
    }


}

@Composable
fun PlanetItem(modifier: Modifier = Modifier, planet: Result, onPlanetClick : (Result) -> Unit = {}) {
    Column(modifier = modifier.clickable {
        onPlanetClick(planet)
    }) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.padding(vertical = 16.dp, horizontal =  16.dp).fillMaxWidth(),
           // colors = CardColors.Default.copy()
        ) {
            Text(planet.name, modifier = Modifier.padding(vertical = 8.dp))
            Text(planet.population, modifier = Modifier.padding(vertical = 8.dp))
            Text(planet.diameter, modifier = Modifier.padding(vertical = 8.dp))
        }

    }
}
