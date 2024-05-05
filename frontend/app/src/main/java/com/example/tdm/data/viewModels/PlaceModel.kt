package com.example.tdm.data.viewModels

import androidx.compose.runtime.mutableStateOf
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

    fun getAllPlaces() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                allPlaces.value = placeRespository.getAllPlaces()
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