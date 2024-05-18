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




    var randomPlaceId: Int? = null

    fun randomlyAvailablePlace(callback: (Int?) -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = placeRespository.getRandomUnreservedPlaceId()
                    if (response.isSuccessful) {
                        val randomPlaceId = response.body()
                        callback(randomPlaceId)
                    } else {
                        // Handle case where the response is not successful
                        callback(null)
                    }
                } catch (e: Exception) {
                    // Handle any exceptions
                    callback(null)
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

