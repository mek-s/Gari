package com.example.tdm

import android.app.Application
import com.example.tdm.data.database
import com.example.tdm.data.repositories.ParkingRepository
import com.example.tdm.data.repositories.PlaceRespository
import com.example.tdm.data.repositories.ReservationRespository
import com.example.tdm.data.repositories.UserRespository

class TDMApplication : Application() {
    private val dataBase by lazy { database.getInstance(this) }
    private val reservationDao by lazy { dataBase.getReservationDao() }
    private val parkingDao by lazy {dataBase.getParkingDao()}
    private val userDao by lazy { dataBase.getUserDao() }
    private val placeDao by lazy { dataBase.getPlaceDao() }

    val reservationRespository by lazy { ReservationRespository(reservationDao) }
    val parkingRepository by lazy {ParkingRepository(parkingDao)}
    val placeRepository by lazy { PlaceRespository(placeDao) }
    val userRespository by lazy { UserRespository(userDao) }

}