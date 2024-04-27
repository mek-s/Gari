package com.example.tdm.data.dataModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tdm.data.models.Parking

import com.example.tdm.data.repositories.ParkingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ParkingModel(private val parkingRepository: ParkingRepository) : ViewModel()  {
    var allRParkings = mutableStateOf(listOf<Parking>())

    fun getAllParkings() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                allRParkings.value = parkingRepository.getAllParkings()
            }
        }

    }

    class Factory(private val parkingRepository: ParkingRepository ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ParkingModel(parkingRepository) as T
        }
    }

}