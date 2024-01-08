package com.example.learncompose.demotestapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learncompose.demotestapp.navigation.BottomNavigationBar
import com.example.learncompose.demotestapp.navigation.NavigationItem
import com.example.learncompose.demotestapp.navigation.Navigations
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun MainScreen(
) {
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberNavController(bottomSheetNavigator)
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }


    Scaffold(
        bottomBar = {
            BottomAppBar(modifier = Modifier) {
                BottomNavigationBar(navController = navController)
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier.height(50.dp),
                onClick = {
                    navController.navigate("bottomsheet")
                }) {
                Icon(Icons.Filled.Add, "Add")
                VerticalDivider(modifier = Modifier.padding(8.dp))
                Icon(Icons.Filled.Face, "Add")
                VerticalDivider(modifier = Modifier.padding(8.dp))
                Icon(Icons.Filled.AccountBox, "Add")
            }
        }
    ) { outerPadding ->

        ModalBottomSheetLayout(
            modifier = Modifier.padding(outerPadding),
            bottomSheetNavigator = bottomSheetNavigator
        ) {
            NavHost(navController, startDestination = NavigationItem.Home.route) {
                composable(NavigationItem.Home.route) {
                    HomeScreenNew()
                }
                composable(NavigationItem.History.route) {
                    HistoryScreen()
                }
                composable(NavigationItem.Profile.route) {
                    ProfileScreen()
                }
                bottomSheet("bottomsheet") {
                    Box(
                        modifier = Modifier
                            .height(1000.dp)
                            .fillMaxWidth()
                    ) {
                        Button(
                            modifier = Modifier,
                            onClick = {
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        showBottomSheet = false
                                    }
                                }
                            }) {
                            Text("Hide bottom sheet")
                        }
                    }
                }
            }
        }
    }
}