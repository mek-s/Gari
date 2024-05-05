package com.example.tdm.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.tdm.data.dataModels.Parking

@Dao
interface ParkingDao {
    @Query("SELECT * FROM parking")
     fun getAllParkings(): List<Parking>

}