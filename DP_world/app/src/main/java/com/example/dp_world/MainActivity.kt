package com.example.dp_world

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dp_world.ui.theme.DP_worldTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DP_worldTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StopWatch( innerPadding)
                }
            }
        }
    }
}


@Composable
private fun StopWatch(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var time by remember {
            mutableStateOf(0L)
        }

        var isStopWatchRunning by remember {
            mutableStateOf(false)
        }

        val scope = rememberCoroutineScope()

        LaunchedEffect(key1 = isStopWatchRunning) {
            while (isStopWatchRunning) {
                delay(10L)
                time += 10
                Log.d("StopWatch", formatTheTime(time))
            }
        }


        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = formatTheTime(time),
        )

        Button(
            onClick = {
                if (!isStopWatchRunning){
                    scope.launch {
                        isStopWatchRunning = true
                    }
                }
            }) {
            Text(text = "Start")

        }
        Button(
            onClick = {
                if(isStopWatchRunning){
                    isStopWatchRunning = false
                }
            }) {
            Text(text = "Stop")

        }
        Button(
            onClick = {
                isStopWatchRunning = false
                time = 0L
            }) {
            Text(text = "Reset")

        }
    }
}

fun formatTheTime(timeInSeconds: Long) : String{
    val hours = timeInSeconds/3600
    val minutes = (timeInSeconds % 3600)/60
    val seconds = timeInSeconds

    return "$hours hours $minutes minute $seconds seconds"
}
