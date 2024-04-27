package com.example.tdm.data.repositories

import User
import UserDao

class UserRespository (private  val userDao : UserDao){
    fun getAllParkings(): List<User> = userDao.getAllUsers()
}