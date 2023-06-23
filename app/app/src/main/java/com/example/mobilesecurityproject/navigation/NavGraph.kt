package com.example.mobilesecurityproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mobilesecurityproject.screens.CreateUserScreen
import com.example.mobilesecurityproject.screens.LoginScreen
import com.example.mobilesecurityproject.screens.MainScreen
import com.example.mobilesecurityproject.viewmodels.LoginViewModel

@Composable
fun SetupNavGraph(
    navController : NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavScreen.Login.route)
    {
        composable(route = NavScreen.Login.route)
        {
            LoginScreen(navController)
        }
        composable(route = NavScreen.CreateAcc.route)
        {
            CreateUserScreen(navController)
        }
        composable(route = NavScreen.MainScreen.route)
        {
            MainScreen(navController = navController)
        }
    }
}