package com.example.learncompose.ui.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TextFieldHomeScreen(navController: NavController) {
    Column(modifier = Modifier.padding(28.dp)) {
        IconRichTextField()
        Spacer(modifier = Modifier.padding(16.dp))
        TextFieldExample()
    }
}

@Composable
fun IconRichTextField() {
    Column(
        modifier = Modifier.padding(top = 20.dp)
    ) {
        var nameText by remember { mutableStateOf("") }
        TextField(
            value = nameText,
            onValueChange = { newString ->
            nameText = newString

        }, label = {
            Text(text = "Enter E-mail")
        }, leadingIcon = {

                Icon(imageVector = Icons.Filled.Email, contentDescription = "email-icon")

        }, trailingIcon = {
                Icon(imageVector = Icons.Filled.Check, contentDescription ="check-icon")
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)


        )
    }
}
@Composable
fun TextFieldExample() {
    var textInput by remember { mutableStateOf("")}
    OutlinedTextField(value = textInput, onValueChange = {textInput = it},
        label = { Text("Username") },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Red,
            unfocusedBorderColor = Color.Blue,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp))
}

@Composable
@Preview
fun previewIcon() {
    IconRichTextField()
}
@Composable
@Preview
fun previewIconTextFieldExample() {
    TextFieldExample()
}