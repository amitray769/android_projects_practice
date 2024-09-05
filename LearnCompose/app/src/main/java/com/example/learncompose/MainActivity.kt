package com.example.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.learncompose.ui.modifier.BringIntoViewResponderModifier
import com.example.learncompose.ui.theme.LearnComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnComposeTheme {
                val navController = rememberNavController()
            /*    NavHost(
                    navController = navController,
                    startDestination = NavigationRoute.HomeRoute.route
                ) {
                    mainNavGraph(navController)
                }*/
                BringIntoViewResponderModifier()
            }
        }
    }
}


class FakeViewModel() {
    val deeplink = mutableStateOf(false)
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

