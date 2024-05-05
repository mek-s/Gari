package com.example.tdm.data.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tdm.Endpoint
import com.example.tdm.data.models.Parking

import com.example.tdm.data.repositories.ParkingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ParkingModel(private val parkingRepository: ParkingRepository) : ViewModel()  {
    var allRParkings = mutableStateOf(listOf<Parking>())
    var loading = mutableStateOf(false)
    var displayMsg = mutableStateOf(false)

    fun getAllParkings() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = parkingRepository.getAllParkings()

                loading.value = false
                if (response.isSuccessful) {
                    val parkings = response.body()
                    if (parkings != null) {
                        allRParkings.value = parkings
                    }
                } else {
                    displayMsg.value = true
                }
            }
        }

    }

    fun saveParking(parking: Parking){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = parkingRepository.getAllParkings()

                loading.value = false
                if (response.isSuccessful) {
                    val parkings = response.body()
                    if (parkings != null) {
                        allRParkings.value = parkings
                    }
                } else {
                    displayMsg.value = true
                }
            }
        }
    }


    class Factory(private val parkingRepository: ParkingRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ParkingModel(parkingRepository) as T
        }
    }

}


