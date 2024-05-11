package com.example.tdm.data.repositories


import com.example.tdm.endpoints.Endpoint
import com.example.tdm.data.models.Reservation
import retrofit2.Response

class ReservationRespository(private val endpoint: Endpoint) {
    suspend fun getAllReservations() : Response<List<Reservation>> = endpoint.getAllResesrvations();
}