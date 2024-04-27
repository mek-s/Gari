package com.example.tdm.data.repositories

import Reservation
import ReservationDao

class ReservationRespository(private val reservationDao: ReservationDao) {
    fun getAllReservations() : List<Reservation> = reservationDao.getAllReservations()
}