package com.example.learncompose.ui.atom

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun LCButton(
    buttonText : String,
    onClick : () -> Unit
){
    Button(
        onClick = onClick) {
        Text(
            text = buttonText
        )
    }
}