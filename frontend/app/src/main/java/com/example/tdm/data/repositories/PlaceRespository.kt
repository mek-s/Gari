package com.example.tdm.data.repositories

import com.example.tdm.endpoints.Endpoint
import com.example.tdm.data.models.Place
import retrofit2.Response


class PlaceRespository(private val endpoint: Endpoint) {
    suspend fun getAllPlaces():  Response<List<Place>> = endpoint.getAllPlaces()
}
