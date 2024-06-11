package com.example.tdm.data.repositories



import Endpoint
import com.example.tdm.data.dao.ReservationDao
import com.example.tdm.data.models.Reservation
import retrofit2.Response

class ReservationRepository(private val endpoint: Endpoint, private val reservationDao: ReservationDao) {


    suspend fun createReservation(reservation: Reservation): Response<Reservation> = endpoint.createReservation(reservation)




    suspend fun getReservationsByUsername(username: String): List<Reservation> {
        return reservationDao.getReservationsByUsername(username)
    }

    suspend fun getReservationsByUsername1(username: String): Response<List<Reservation>> {
        return endpoint.getReservationsByUsername(username)
    }

    suspend fun getReservationById(id: Int): Reservation? {
        return try {
            val response = endpoint.getReservationById(id)
            if (response.isSuccessful) {
                val reservation = response.body()
                if (reservation != null) {
                    reservationDao.insertReservation(reservation)
                }
                reservation
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

}
