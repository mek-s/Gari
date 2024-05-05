package com.example.tdm.data.repositories


import com.example.tdm.Endpoint
import com.example.tdm.data.models.Parking
import parkingEndpoint
import retrofit2.Response

class ParkingRepository(private val endpoint: Endpoint) {

    suspend fun getAllParkings(): Response<List <Parking>> = endpoint.getAllParkings()
}



