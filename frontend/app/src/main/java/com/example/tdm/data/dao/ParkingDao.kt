package com.example.tdm.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tdm.data.models.Parking

@Dao
interface ParkingDao {

    @Insert
    fun saveParking(parking: Parking)

    @Query("SELECT * FROM parking")
     fun getAllParkings(): List<Parking>
     @Query("SELECT * FROM parking WHERE idParking =:id")
     fun getParkingById(id :Int) : Parking


}