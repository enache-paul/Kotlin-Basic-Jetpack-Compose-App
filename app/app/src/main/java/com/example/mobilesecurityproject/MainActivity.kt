package com.example.mobilesecurityproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobilesecurityproject.navigation.SetupNavGraph
import com.example.mobilesecurityproject.ui.theme.MobileSecurityProjectTheme


class MainActivity : ComponentActivity() {

    lateinit var navController : NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileSecurityProjectTheme {
                navController = rememberNavController()

                SetupNavGraph(navController = navController)
            }
        }
    }
}

