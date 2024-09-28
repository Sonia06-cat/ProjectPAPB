package com.tifd.projectpapb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var submittedName by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)) // Background color
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Input field
            BasicTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier
                    .background(Color.DarkGray, RectangleShape)
                    .padding(16.dp)
                    .fillMaxWidth(0.8f),
                decorationBox = { innerTextField ->
                    if (name.text.isEmpty()) {
                        Text(
                            text = "Masukkan nama",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Submit Button
            Button(
                onClick = {
                    submittedName = name.text  // Save the submitted name
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD0BCFF)),
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                Text("Submit", textAlign = TextAlign.Center)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Display submitted name
            if (submittedName.isNotEmpty()) {
                Text(
                    text = " $submittedName",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}