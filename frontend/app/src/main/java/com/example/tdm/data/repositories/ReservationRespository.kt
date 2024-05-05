package com.example.tdm.data.repositories


import com.example.tdm.Endpoint
import com.example.tdm.data.dao.ReservationDao
import com.example.tdm.data.models.Reservation
import retrofit2.Response

class ReservationRespository(private val endpoint: Endpoint) {
    suspend fun getAllReservations() : Response<List<Reservation>> = endpoint.getAllResesrvations();
}