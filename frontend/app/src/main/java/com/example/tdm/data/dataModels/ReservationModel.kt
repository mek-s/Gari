package com.example.tdm.data.dataModels


import com.example.tdm.data.repositories.ReservationRespository

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tdm.data.models.Reservation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ReservationModel(private val reservationRespository: ReservationRespository) : ViewModel() {

    var allReservations = mutableStateOf(listOf<Reservation>())

    fun getAllReservations() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                allReservations.value = reservationRespository.getAllReservations()
            }
        }

    }

    class Factory(private val reservationRespository: ReservationRespository ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ReservationModel(reservationRespository) as T
        }
    }
}