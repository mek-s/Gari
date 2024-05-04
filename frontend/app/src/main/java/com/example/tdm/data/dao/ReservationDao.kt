package com.example.tdm.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.tdm.data.models.Reservation


@Dao
interface ReservationDao {
    @Query("SELECT * FROM reservation")
     fun getAllReservations(): List<Reservation>

}