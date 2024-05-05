package com.example.tdm.data.repositories


import com.example.tdm.data.dao.ReservationDao
import com.example.tdm.data.models.Reservation

class ReservationRespository(private val reservationDao: ReservationDao) {
    fun getAllReservations() : List<Reservation> = reservationDao.getAllReservations()
}