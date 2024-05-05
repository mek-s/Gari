package com.example.tdm.data.repositories

import com.example.tdm.Endpoint
import com.example.tdm.data.dao.PlaceDao
import com.example.tdm.data.models.Parking
import com.example.tdm.data.models.Place
import parkingEndpoint
import retrofit2.Response


class PlaceRespository(private val endpoint: Endpoint) {
    suspend fun getAllPlaces():  Response<List<Place>> = endpoint.getAllPlaces()
}
