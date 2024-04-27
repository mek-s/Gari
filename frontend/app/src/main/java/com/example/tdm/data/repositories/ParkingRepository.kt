package com.example.tdm.data.repositories



import ParkingDao
import com.example.tdm.data.models.Parking
class ParkingRepository(private val parkingDao: ParkingDao) {
    fun getAllParkings(): List<Parking> = parkingDao.getAllParkings()
}