package com.example.tdm.data.repositories



import Endpoint
import com.example.tdm.data.models.Reservation
import retrofit2.Response

class ReservationRespository(private val endpoint: Endpoint) {
    suspend fun getAllReservations(): Response<List<Reservation>> = endpoint.getAllResesrvations()

    suspend fun createReservation(reservation: Reservation): Response<Reservation> = endpoint.createReservation(reservation)

    suspend fun getReservationsByUsername(username: String): Response<List<Reservation>> = endpoint.getReservationsByUsername(username)
}