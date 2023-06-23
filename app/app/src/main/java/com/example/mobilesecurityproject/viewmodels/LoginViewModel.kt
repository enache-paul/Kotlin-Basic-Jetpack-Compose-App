package com.example.mobilesecurityproject.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilesecurityproject.database.UserDatabase
import com.example.mobilesecurityproject.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope

class LoginViewModel : ViewModel() {

    fun getRepo(currentContext: Context) : UserRepository {
        val userDao = UserDatabase.getDatabase(
            currentContext
        ).userDao()
        return UserRepository(userDao)
    }


    fun getScope() : CoroutineScope{
        return viewModelScope
    }




}