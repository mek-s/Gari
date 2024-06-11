package com.example.tdm.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tdm.data.models.Reservation


@Dao
interface ReservationDao {
    @Query("SELECT * FROM reservation")
    fun getAllReservations(): List<Reservation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReservation(reservation: Reservation)

    @Query("SELECT * FROM reservation WHERE username = :username")
    fun getReservationsByUsername(username: String): List<Reservation>

    @Query("DELETE FROM reservation WHERE username = :username")
    fun deleteReservationsByUsername(username: String)

    @Query("SELECT * FROM reservation WHERE id_reservation = :id")
    fun getReservationById(id: Int): Reservation?
}
