package com.example.tdm.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tdm.data.models.Reservation
import com.example.tdm.data.models.User


@Dao
interface ReservationDao {
    @Query("SELECT * FROM reservation")
     fun getAllReservations(): List<Reservation>

    @Insert
    fun insertReservation(r: Reservation)

}