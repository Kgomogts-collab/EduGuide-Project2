package com.example.edu_login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.example.edu_login.auth.LoginScreen
import com.example.edu_login.data.AppDatabase
import com.example.edu_login.data.User
import com.example.edu_login.ui.theme.EDUGuideTheme
import com.example.edu_login.util.PasswordUtils
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       
        lifecycleScope.launch {
            val dao = AppDatabase.getInstance(this@MainActivity).userDao()
            if (!dao.emailExists("ThaboMolal@gmail.com")) {
                dao.insertUser(
                    User(
                        firstName = "Thabo",
                        lastName = "Molala",
                        email = "ThaboMolal@gmail.com",
                        passwordHash = PasswordUtils.sha256("Jemidiya19")
                    )
                )
            }
        }

       
        setContent {
            EDUGuideTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // State to track if logged in
                    var isLoggedIn by remember { mutableStateOf(false) }

                    if (isLoggedIn) {
                        // Temporary success screen
                        Text(
                            text = "Welcome to EDU-Guide!\nSuccessfully Logged In.",
                            fontSize = 24.sp,
                            modifier = Modifier.padding(24.dp)
                        )
                    } else {
                        LoginScreen(
                            onLoginSuccess = { isLoggedIn = true }
                        )
                    }
                }
            }
        }
    }
}