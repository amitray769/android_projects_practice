package com.example.learncompose.ui.bottomsheet

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.example.learncompose.ui.textfield.TextFieldHomeScreen
import kotlinx.coroutines.launch
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.SheetState
import com.example.learncompose.ui.home.HomeScreen
import kotlinx.coroutines.CoroutineScope


@Composable
fun BottomSheetScreen(navController: NavController) {
    BottomSheetWithFAB()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleBottomSheet(navController: NavController) {
    ModalBottomSheet(onDismissRequest = { /* Executed when the sheet is dismissed */ }) {
        // Sheet content
        TextFieldHomeScreen(navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetWithFAB() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show bottom sheet") },
                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                onClick = {
                    showBottomSheet = true
                }
            )
        }
    ) { contentPadding ->
        // Screen content

        if (showBottomSheet) {
            BottomSheetContainer(sheetState = sheetState, scope = scope){
                showBottomSheet = it
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContainer(
    sheetState : SheetState,
    scope: CoroutineScope,
    showBottomSheet :(Boolean)-> Unit
) {
    ModalBottomSheet(
        onDismissRequest = {
             showBottomSheet.invoke(false)
        },
        sheetState = sheetState
    ) {
        // Sheet content
        Button(onClick = {
            scope.launch { sheetState.hide() }.invokeOnCompletion {
                if (!sheetState.isVisible) {
                    showBottomSheet.invoke(false)
                }
            }
        }) {
            Text("Hide bottom sheet")
        }
    }

}

@Composable
fun ModelBottomSheetLayoutExample() {
  //  ModelBottomSheetLayout()

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScaffold(navController : NavController) {
    BottomSheetScaffold(sheetContent =
    { TextFieldHomeScreen(navController = navController) }) {
        HomeScreen(navController = navController)
    }
}