package com.example.learncompose.ui.bottomsheet


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun ModalBottomSheetWithCloseButton() {


    // Combine bottom sheet content and close button
  /*  val combinedBottomSheetContent = @Composable {
        Column {
            bottomSheetCloseButton()
            bottomSheetContent()
        }
    }*/

      //  PBBottomSheet(sheetState = bottomSheetState, scope = scope, bottomSheetCloseButton = bottomSheetCloseButton,  sheetContent = bottomSheetContent, showBottomSheet = {showBottomSheet= it})


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
 fun PBBottomSheet(
    bottomSheetState: SheetState,
    sheetContent: @Composable() (ColumnScope.() -> Unit),
    onCloseClick: () -> Unit,
) {
    var showCloseButton by remember { mutableStateOf(bottomSheetState.isVisible) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = bottomSheetState.isVisible)
    {
        showCloseButton = bottomSheetState.isVisible
    }

    // Bottom sheet close button
    val bottomSheetCloseButton = @Composable {
        IconButton(
            onClick = {
                coroutineScope.launch {
                    // Hide the bottom sheet when the close button is clicked
                    bottomSheetState.hide()
                    onCloseClick()
                    showCloseButton = false
                }
            },
            modifier = Modifier
                .padding(16.dp)
                .background(
                    color = Color.White,
                    shape = CircleShape
                )
        ) {
            Icon(imageVector = Icons.Default.Close, contentDescription = null)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        if (showCloseButton){
            bottomSheetCloseButton.invoke()
        }

        Spacer(modifier = Modifier.padding(bottom = 8.dp))

        ModalBottomSheet(
            onDismissRequest = onCloseClick,
            sheetState = bottomSheetState,
            content = sheetContent
        )
    }

}

