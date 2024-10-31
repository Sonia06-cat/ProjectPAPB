package com.tifd.projectpapb

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        setContent {
            MyApp(auth)
        }
    }
}

@Composable
fun MyApp(auth: FirebaseAuth) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BasicTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .background(Color.DarkGray, RectangleShape)
                    .padding(16.dp)
                    .fillMaxWidth(0.8f),
                textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                decorationBox = { innerTextField ->
                    if (email.text.isEmpty()) {
                        Text(text = "Enter email", color = Color.Gray, fontSize = 16.sp)
                    }
                    innerTextField()
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            BasicTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .background(Color.DarkGray, RectangleShape)
                    .padding(16.dp)
                    .fillMaxWidth(0.8f),
                textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                decorationBox = { innerTextField ->
                    if (password.text.isEmpty()) {
                        Text(text = "Enter password", color = Color.Gray, fontSize = 16.sp)
                    }
                    innerTextField()
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    auth.signInWithEmailAndPassword(email.text, password.text)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val intent = Intent(context, ListActivity::class.java)
                                context.startActivity(intent)
                            } else {
                                Toast.makeText(context, "Login failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD0BCFF)
                ),
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                Text("Login", textAlign = TextAlign.Center)
            }
        }
    }
}
