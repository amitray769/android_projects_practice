package com.example.learncompose.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
private fun ToggleShape(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            var isVisible by remember {
                mutableStateOf(false)
            }
            var isRound by remember {
                mutableStateOf(false)
            }
            Button(onClick = {
                isVisible = !isVisible
                isRound = !isRound
            }) {
                Text(text = "toggle")
            }
            val borderRadius by animateIntAsState(
                animationSpec = tween(durationMillis = 400),
                targetValue = if (isRound) 100 else 0
            )
            val color by animateColorAsState(
                animationSpec = tween(1000),
                targetValue = if (isRound) Color.Green else Color.Red
            )

            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(borderRadius))
                    .background(color)
            )
            /*      AnimatedVisibility(
                      visible = isVisible,
                      enter = slideInHorizontally() + fadeIn(),
                      modifier = Modifier
                          .fillMaxWidth()
                          .weight(1f)
                  ) {
                      Box(modifier = Modifier.background(color = Color.Red))

                  }*/
        }
    }
}