package com.example.learncompose.ui.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun CircleCustomShape(modifier: Modifier = Modifier) {
    // Draw a circle
    Canvas(modifier = modifier) {
        drawCircle(
            radius = 300f,
            color = Color.Red
        )
    }

    Canvas(modifier = modifier) {
        drawCircle(
            radius = 200f,
            color = Color.Green
        )
    }

    Canvas(modifier = modifier) {
        drawCircle(
            radius = 100f,
            color = Color.Blue
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CircleCustomPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircleCustomShape()

    }

}