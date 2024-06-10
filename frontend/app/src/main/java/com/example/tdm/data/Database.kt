package com.example.tdm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.tdm.data.models.Parking
import com.example.tdm.data.models.Place
import com.example.tdm.data.dao.ParkingDao
import com.example.tdm.data.dao.PlaceDao
import com.example.tdm.data.dao.ReservationDao
import com.example.tdm.data.dao.UserDao
import com.example.tdm.data.models.Reservation
import com.example.tdm.data.models.User

@Database(entities = [Parking::class ,Place::class, Reservation::class , User::class ], version = 4)
@TypeConverters(Converters::class)
abstract class database : RoomDatabase() {
    abstract fun getParkingDao(): ParkingDao
    abstract fun getReservationDao() : ReservationDao
    abstract fun getPlaceDao() : PlaceDao
    abstract fun getUserDao() : UserDao

    companion object {
        private var INSTANCE: database? = null
        private const val DATABASE_NAME = "gari_database"

        fun getInstance(context: Context): database {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        database::class.java,
                        DATABASE_NAME
                    )
                        // Define migration from version 3 to 4
                        .addMigrations(MIGRATION_3_4)
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

        // Migration from version 3 to 4
        private val MIGRATION_3_4: Migration = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `reservation_new` (`id_reservation` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `id_place` INTEGER NOT NULL, `idParking` INTEGER NOT NULL, `date` TEXT NOT NULL, `heure_entree` TEXT NOT NULL, `heure_sortie` TEXT NOT NULL, `codeQr` TEXT NOT NULL, `prix` REAL NOT NULL, `username` TEXT NOT NULL)")
                database.execSQL("INSERT INTO reservation_new (id_reservation, id_place, idParking, date, heure_entree, heure_sortie, codeQr, prix, username) SELECT id_reservation, id_place, idParking, date, heure_entree, heure_sortie, codeQr, prix, username FROM reservation")
                database.execSQL("DROP TABLE reservation")
                database.execSQL("ALTER TABLE reservation_new RENAME TO reservation")
            }
        }


    }
    }



