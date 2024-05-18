package com.example.tdm.data.repositories

import Endpoint
import com.example.tdm.data.models.Place
import retrofit2.Response


class PlaceRespository(private val endpoint: Endpoint) {
    suspend fun getAllPlaces(): Response<List<Place>> = endpoint.getAllPlaces()

    suspend fun getRandomUnreservedPlaceId(): Response<Int?> = endpoint.getRandomUnreservedPlaceId()

}