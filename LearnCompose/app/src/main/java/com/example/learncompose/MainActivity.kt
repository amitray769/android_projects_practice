package com.example.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.learncompose.demotestapp.MainScreen
import com.example.learncompose.navigation.NavigationRoute
import com.example.learncompose.navigation.mainNavGraph
import com.example.learncompose.ui.bottomsheet.BottomSheetWithFAB
import com.example.learncompose.ui.theme.LearnComposeTheme

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnComposeTheme {

             /*    NavHost(
                    navController = navController,
                    startDestination = NavigationRoute.HomeRoute.route) {
                    mainNavGraph(navController)
                }*/
//                MainScreen()
                BottomSheetWithFAB()
            }
        }
    }
}




class FakeViewModel(){
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

