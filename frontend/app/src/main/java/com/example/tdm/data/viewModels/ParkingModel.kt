package com.example.tdm.data.viewModels

import android.util.Log
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
    var parking = mutableStateOf<Parking?>(null)
    var loading = mutableStateOf(false)
    var displayMsg = mutableStateOf(false)

    fun getAllParkings() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = parkingRepository.getAllParkings()

                    Log.d("ParkingModel", "Response code: ${response.code()}")

                    loading.value = false
                    if (response.isSuccessful) {
                        val parkings = response.body()
                        if (parkings != null) {
                            allRParkings.value = parkings
                            Log.d("ParkingModel", "Parkings received: $parkings")
                        }
                    } else {
                        displayMsg.value = true
                        Log.e("ParkingModel", "Failed to fetch parkings: ${response.message()}")
                    }
                } catch (e: Exception) {
                    Log.e("ParkingModel", "Exception: ${e.message}")
                    displayMsg.value = true
                }
            }
        }
    }

    fun getParkingById(id:Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = parkingRepository.getParkingById(id)

                    Log.d("ParkingModel", "Response code: ${response.code()}")

                    loading.value = false
                    if (response.isSuccessful) {
                        val res = response.body()
                        if (res != null) {
                            parking.value = res
                            Log.d("ParkingModel", "Parkings received: ${parking.value}")
                        }
                    } else {
                        displayMsg.value = true
                        Log.e("ParkingModel", "Failed to fetch parkings: ${response.message()}")
                    }
                } catch (e: Exception) {
                    Log.e("ParkingModel", "Exception: ${e.message}")
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
