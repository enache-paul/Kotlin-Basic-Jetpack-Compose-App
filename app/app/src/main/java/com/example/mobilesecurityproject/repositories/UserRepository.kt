package com.example.mobilesecurityproject.repositories

import androidx.lifecycle.LiveData
import com.example.mobilesecurityproject.database.dao.UserDao
import com.example.mobilesecurityproject.models.User

class UserRepository(private val userDao: UserDao) {

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun loginUser(username : String, password : String) : User? {
        return userDao.loginUser(username, password)
    }
}