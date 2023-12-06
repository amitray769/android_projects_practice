package com.example.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learncompose.ui.theme.LearnComposeTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnComposeTheme {
                // A surface container using the 'background' color from the theme
                val value= remember {
                    mutableStateOf<String>("he000000lo")
                }
                for (i in 1..10){
                    value.value = "hello_$i"
            }
                LaunchEffectExample(value, FakeViewModel())
            }
        }
    }
}

class FakeViewModel(){
    val deeplink = mutableStateOf(false)
}

@Composable
private fun LaunchEffectExample(
    value: MutableState<String>,
    fakeViewModel: FakeViewModel
){
    val insideValue = fakeViewModel.deeplink.value
    for (i in 1..10){
        println(i)
    }

LaunchedEffect(key1 = 3, block ={
    if (insideValue){
        println("Hello world")
        println(value.value)
    }
} )
    LaunchedEffect(key1 = 2, block ={
    println("Hello Cat")
    println(value.value)
} )
    LaunchedEffect(key1 = 2, block ={
    println("Hello Dog")
    println(value.value)
} )
    LaunchedEffect(key1 = insideValue,){
        delay(1000)
        println("bye")
 fakeViewModel.deeplink.value = true
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LearnComposeTheme {
        Greeting("Android")
    }
}

@Composable
private fun toggleShape(){
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
                animationSpec =  tween(durationMillis = 400) ,
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