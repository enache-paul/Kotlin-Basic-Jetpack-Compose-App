package com.example.mobilesecurityproject.navigation

sealed class NavScreen(val route : String){
    object Login : NavScreen(route = "login_screen")
    object CreateAcc : NavScreen(route = "create_screen")
    object MainScreen : NavScreen(route = "main_screen")
}

