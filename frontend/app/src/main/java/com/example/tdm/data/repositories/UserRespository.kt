package com.example.tdm.data.repositories


import com.example.tdm.data.dao.UserDao
import com.example.tdm.data.dataModels.User

class UserRespository (private  val userDao : UserDao){
    fun getAllUsers(): List<User> = userDao.getAllUsers()
}