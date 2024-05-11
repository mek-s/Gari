package com.example.tdm.data.viewModels

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
    private val _allPlaces = MutableLiveData<List<Place>>()
    val allPlaces: LiveData<List<Place>> get() = _allPlaces

    fun getAllPlaces() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = placeRespository.getAllPlaces()
                if (response.isSuccessful) {
                    val places: List<Place>? = response.body()
                    // Update LiveData with the list of places
                    _allPlaces.postValue(places ?: emptyList())

                } else {
                    // Handle unsuccessful response
                    println("Failed to fetch places: ${response.message()}")
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
