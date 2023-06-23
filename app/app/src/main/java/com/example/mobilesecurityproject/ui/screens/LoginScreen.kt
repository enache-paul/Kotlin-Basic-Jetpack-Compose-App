package com.example.mobilesecurityproject.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.mobilesecurityproject.R
import com.example.mobilesecurityproject.navigation.NavScreen
import com.example.mobilesecurityproject.viewmodels.LoginViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobilesecurityproject.models.User
import com.example.mobilesecurityproject.repositories.UserRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.reflect.typeOf


@Composable
fun LoginScreen(
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
            LoginSection(
                modifier = Modifier.padding(contentPadding),
                "Log in",
                repo = repository,
                viewModel = viewModel,
                navController = navController
            )
            GoToClickableText(
                text = "create account",
                action = {
                    navController.navigate(route = NavScreen.CreateAcc.route)
                }
            )
        }
    }

}


@Composable
fun AppBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .padding(vertical = 10.dp, horizontal = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Text(
            text = stringResource(R.string.app_name),
            modifier = Modifier.padding(start = 15.dp),
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h6,
        )
        Icon(
            modifier = Modifier.padding(horizontal = 20.dp),
            painter = painterResource(id = R.drawable.baseline_adb_24),
            contentDescription = "Android icon",
            tint = Color.Green
        )
    }
}


@OptIn(DelicateCoroutinesApi::class)
@Composable
fun LoginSection(
    modifier : Modifier = Modifier,
    buttonText : String,
    repo : UserRepository,
    viewModel: LoginViewModel,
    navController: NavController
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

        val context = LocalContext.current
        Button(
            onClick = {
                viewModel.getScope().launch {
                    val correctCredentials = validUserCredentials(username = username, password = password, userRepository = repo)
                    if (correctCredentials) {
                        navController.navigate(NavScreen.MainScreen.route)
                        Toast.makeText(context, "logged in", Toast.LENGTH_LONG).show()
                    }
                    else
                        Toast.makeText(context, "Incorrect username or password", Toast.LENGTH_LONG).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .size(40.dp),

        ) {
            Text(text = buttonText)
        }
    }
}

suspend fun validUserCredentials(username : String, password : String, userRepository: UserRepository) : Boolean {
    return (userRepository.loginUser(username, password) != null)
}

@Composable
fun GoToClickableText(text : String, action : () -> (Unit)) {
    Column {
        ClickableText(
            modifier = Modifier.padding(20.dp),
            text = AnnotatedString(text),
            style = TextStyle(
                color = Color.Black,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            ),
            onClick = {action()}
        )
    }
}
