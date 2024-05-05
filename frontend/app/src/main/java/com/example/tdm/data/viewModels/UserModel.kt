package com.example.tdm.data.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tdm.data.models.User
import com.example.tdm.data.repositories.UserRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserModel (private val userRespository: UserRespository) : ViewModel() {

    var allUsers = mutableStateOf(listOf<User>())

    fun getAllReservations() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                allUsers.value = userRespository.getAllUsers()
            }
        }

    }

    class Factory(private val userRespository: UserRespository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserModel(userRespository) as T
        }
    }
}