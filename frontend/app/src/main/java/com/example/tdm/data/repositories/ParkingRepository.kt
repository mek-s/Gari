package com.example.tdm.data.repositories


import com.example.tdm.data.dao.ParkingDao
import com.example.tdm.data.dataModels.Parking
import parkingEndpoint
import retrofit2.Response

class ParkingRepository(private val endpoint: parkingEndpoint) {

    suspend fun getAllParkings(): Response<List <Parking>> = endpoint.getAllParkings()
}



