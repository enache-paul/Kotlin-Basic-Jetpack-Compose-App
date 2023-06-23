package com.example.mobilesecurityproject.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mobilesecurityproject.models.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM users_table WHERE username = :username AND password = :password LIMIT 1 ")
    suspend fun loginUser(username: String, password: String): User?
}