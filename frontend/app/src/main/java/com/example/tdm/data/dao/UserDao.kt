package com.example.tdm.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tdm.data.models.User


@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    fun getUserByUsernameAndPassword(username: String, password: String): User?


    @Insert
    fun insertUser(user: User)

}