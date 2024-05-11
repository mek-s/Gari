package com.example.tdm.data.repositories


import android.util.Log
import com.example.tdm.data.models.Parking
import parkingEndpoint
import retrofit2.Response

class ParkingRepository(private val endpoint: parkingEndpoint) {

    suspend fun getAllParkings(): Response<List<Parking>> {
        try {
            val response = endpoint.getAllParkings()
            Log.d("ParkingRepository", "Response code: ${response.code()}")
            return response
        } catch (e: Exception) {
            Log.e("ParkingRepository", "Exception: ${e.message}")
            throw e
        }
    }

}



