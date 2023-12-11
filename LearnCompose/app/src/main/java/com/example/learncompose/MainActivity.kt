package com.example.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.learncompose.navigation.NavigationRoute
import com.example.learncompose.navigation.mainNavGraph
import com.example.learncompose.ui.theme.LearnComposeTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnComposeTheme {
                 navController = rememberNavController()
                 NavHost(
                    navController = navController,
                    startDestination = NavigationRoute.HomeRoute.route) {
                    mainNavGraph(navController)
                }
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