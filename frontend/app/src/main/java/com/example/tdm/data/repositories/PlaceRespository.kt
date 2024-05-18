package com.example.tdm.data.repositories

import Endpoint
import com.example.tdm.data.models.Place
import retrofit2.Response


class PlaceRespository(private val endpoint: Endpoint) {
    suspend fun getAllPlaces(): Response<List<Place>> = endpoint.getAllPlaces()

    suspend fun getRandomUnreservedPlaceId(parkingId: Int): Response<Int?> = endpoint.getRandomUnreservedPlaceId(parkingId)


    suspend fun reservePlace(placeId: Int): Response<Unit> { return endpoint.reservePlace(placeId) }

}