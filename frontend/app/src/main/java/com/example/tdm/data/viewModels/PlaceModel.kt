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

class PlaceModel(private val placeRespository: PlaceRespository) : ViewModel() {

    var allPlaces = mutableStateOf(listOf<Place>())
    var loading = mutableStateOf(false)
    var displayMsg = mutableStateOf(false)


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

    class Factory(private val placeRespository: PlaceRespository ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PlaceModel(placeRespository) as T
        }
    }



}

