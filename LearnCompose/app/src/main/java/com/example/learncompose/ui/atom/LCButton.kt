package com.example.learncompose.ui.atom

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LCButton(
    buttonText : String,
    modifier: Modifier = Modifier,
    onClick : () -> Unit
){
    Button(
        modifier = modifier,
        onClick = onClick) {
        Text(
            text = buttonText
        )
    }
}