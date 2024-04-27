package com.example.tdm.data.repositories


import com.example.tdm.data.dao.UserDao
import com.example.tdm.data.models.User

class UserRespository (private  val userDao : UserDao){
    fun getAllUsers(): List<User> = userDao.getAllUsers()
}