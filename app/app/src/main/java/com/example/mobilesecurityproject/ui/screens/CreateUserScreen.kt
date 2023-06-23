package com.example.mobilesecurityproject.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mobilesecurityproject.models.User
import com.example.mobilesecurityproject.navigation.NavScreen
import com.example.mobilesecurityproject.repositories.UserRepository
import com.example.mobilesecurityproject.viewmodels.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun CreateUserScreen(
    navController: NavController,
    viewModel : LoginViewModel = viewModel()
) {

    val repository = viewModel.getRepo(LocalContext.current)

    Scaffold(
        topBar = {
            AppBar()
        }
    ) { contentPadding ->
        Column {
            CreateUserSection(
                modifier = Modifier.padding(contentPadding),
                "Create account",
                repo = repository,
                navController = navController,
                viewModel = viewModel
            )
            GoToClickableText(
                text = "log in",
                action = {
                    navController.navigate(NavScreen.Login.route)
                })
        }

    }
}

@Composable
fun CreateUserSection(
    modifier : Modifier = Modifier,
    buttonText : String,
    repo : UserRepository,
    navController : NavController,
    viewModel: LoginViewModel
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = {username = it},
            label = { Text("Username") },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password ,
            onValueChange = {password = it},
            label = { Text("Password") },
            singleLine = true,
            maxLines = 1,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val user = User(id = 0, username = username, password = password)
                viewModel.getScope().launch {
                    repo.addUser(user)
                }
                navController.navigate(NavScreen.Login.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .size(40.dp),

            ) {
            Text(text = buttonText)
        }
    }
}
