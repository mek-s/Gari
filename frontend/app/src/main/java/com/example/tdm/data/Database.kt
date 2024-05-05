package com.example.tdm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tdm.data.models.Parking
import com.example.tdm.data.models.Reservation
import com.example.tdm.data.models.Place
import com.example.tdm.data.models.User
import com.example.tdm.data.dao.ParkingDao
import com.example.tdm.data.dao.PlaceDao
import com.example.tdm.data.dao.ReservationDao
import com.example.tdm.data.dao.UserDao
@Database(entities = [Parking::class ,Place::class, Reservation::class , User::class ], version = 2)
@TypeConverters(Converters::class)
abstract class database : RoomDatabase() {
    abstract fun getParkingDao(): ParkingDao
    abstract fun getReservationDao() : ReservationDao
    abstract fun getPlaceDao() : PlaceDao
    abstract fun getUserDao() : UserDao


    companion object {
        private var INSTANCE: database? = null
        fun getInstance(context: Context): database {
            var instance = INSTANCE
            synchronized(this) {
                if (instance == null) {
                    instance =
                        Room.databaseBuilder(
                            context, database::class.java,
                            "gari_database"
                        ).build()
                    INSTANCE = instance
                }
                return instance as database
            }
        }
    }
}

