package com.example.tdm.data.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tdm.data.models.Place
import com.example.tdm.data.repositories.PlaceRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class PlaceModel(private val placeRespository: PlaceRespository) : ViewModel() {

    var allPlaces = mutableStateOf(listOf<Place>())
    var loading = mutableStateOf(false)
    var displayMsg = mutableStateOf(false)
    val randomPlace = mutableStateOf(0)


    fun getAllPlaces() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = placeRespository.getAllPlaces()

                loading.value = false

                if (response.isSuccessful) {
                    val places = response.body()
                    if (places != null) {
                        allPlaces.value = places
                    }
                } else {
                    displayMsg.value = true

                }
            }
        }
    }
    fun getAvailablePlaces(parkingId: Int, callback: (List<Place>?) -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                loading.value = true
                val response = placeRespository.getAvailablePlaces(parkingId)
                loading.value = false

                if (response.isSuccessful) {
                    val places = response.body()
                    callback(places)
                } else {
                    displayMsg.value = true
                    callback(null)
                }
            }
        }
    }




    var randomPlaceId: Int? = null

    fun randomlyAvailablePlace(parkingId: Int, callback: (Int?) -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = placeRespository.getRandomUnreservedPlaceId(parkingId)
                    if (response.isSuccessful) {
                        val randomPlaceId = response.body()
                        callback(randomPlaceId)
                    } else {
                        callback(null)
                    }
                } catch (e: Exception) {
                    callback(null)
                }
            }
        }
    }


    fun reservePlace(placeId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = placeRespository.reservePlace(placeId)
                    if (response.isSuccessful) {

                    } else {

                    }
                } catch (e: Exception) {

                }
            }
        }
    }




    class Factory(private val placeRespository: PlaceRespository ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PlaceModel(placeRespository) as T
        }
    }



}

