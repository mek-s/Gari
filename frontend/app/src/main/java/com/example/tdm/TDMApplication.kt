
package com.example.tdm

import SharedPreferencesManager
import UserRepository
import android.app.Application
import com.example.tdm.data.database
import com.example.tdm.data.repositories.ParkingRepository
import com.example.tdm.data.repositories.PlaceRespository
import com.example.tdm.data.repositories.ReservationRepository



class TDMApplication : Application() {
    private val dataBase by lazy { database.getInstance(this) }
    val reservationDao by lazy { dataBase.getReservationDao() }
    private val parkingDao by lazy {dataBase.getParkingDao()}
    private val userDao by lazy { dataBase.getUserDao() }
    private val placeDao by lazy { dataBase.getPlaceDao() }

    val reservationRespository by lazy { ReservationRepository(Endpoint.createEndpoint(), reservationDao) }
    val parkingRepository by lazy {ParkingRepository(Endpoint.createEndpoint())}
    val placeRepository by lazy { PlaceRespository(Endpoint.createEndpoint()) }
//    val userRespository by lazy { UserRespository(userDao) }
    val userRespository by lazy { UserRepository(Endpoint.createEndpoint()) }
    val sharedPreferencesManager by lazy { SharedPreferencesManager(applicationContext) }

}


