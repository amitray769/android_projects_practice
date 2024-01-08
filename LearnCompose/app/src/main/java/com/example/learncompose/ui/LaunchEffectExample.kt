package com.example.learncompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import com.example.learncompose.FakeViewModel
import kotlinx.coroutines.delay

@Composable
fun LaunchEffectExample(
    value: MutableState<String>,
    fakeViewModel: FakeViewModel
){
    val insideValue = fakeViewModel.deeplink.value
    for (i in 1..10){
        println(i)
    }

    LaunchedEffect(key1 = 3, block = {
        if (insideValue) {
            println("Hello world")
            println(value.value)
        }
    })
    LaunchedEffect(key1 = 2, block = {
        println("Hello Cat")
        println(value.value)
    })
    LaunchedEffect(key1 = 2, block = {
        println("Hello Dog")
        println(value.value)
    })
    LaunchedEffect(key1 = insideValue,) {
        delay(1000)
        println("bye")
        fakeViewModel.deeplink.value = true
    }
}