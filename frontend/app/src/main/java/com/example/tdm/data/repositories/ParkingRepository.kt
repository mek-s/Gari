package com.example.tdm.data.repositories
import Endpoint
import android.util.Log
import com.example.tdm.data.models.Parking
import retrofit2.Response

class ParkingRepository(private val endpoint: Endpoint) {

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

    suspend fun getParkingById(id : Int): Response<Parking> {
        try {
            val response = endpoint.getParkingById(id)
            Log.d("ParkingRepository", "Response code: ${response.code()}")
            return response
        } catch (e: Exception) {
            Log.e("ParkingRepository", "Exception: ${e.message}")
            throw e
        }
    }

    suspend fun getParkingTariffById(id: Int): Response<Double> {
        try {
            val response = endpoint.getParkingTariffById(id)
            Log.d("ParkingRepository", "Response code: ${response.code()}")
            return response
        } catch (e: Exception) {
            Log.e("ParkingRepository", "Exception: ${e.message}")
            throw e
        }
    }


}


