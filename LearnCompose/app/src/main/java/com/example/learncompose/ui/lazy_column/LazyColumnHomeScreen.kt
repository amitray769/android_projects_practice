package com.example.learncompose.ui.lazy_column

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.learncompose.ui.atom.LCButton

/**
* Lazy Column is used when you need scrolling, If you don't need scrolling then consider using Column here.
 * LazyColumn emit each item's content by iterating over a list
*/

//todo we can create here the list of multiple lazy columns and pass it to
@Composable
fun LazyColumnHomeScreen(){
    val itemList = mutableListOf<String>()
    for (i in 1..15)
    {
        itemList.add("Item $i")
    }
   // LazyColumn(content = )

    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally) {
        for (item in itemList){
            LCButton(buttonText = item, modifier = Modifier.padding(vertical = 16.dp)) {

            }
        }
    }
}