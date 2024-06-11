package com.example.tdm.data

import android.content.Context
import android.database.sqlite.SQLiteException
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

@Database(entities = [Parking::class ,Place::class, Reservation::class , User::class ], version = 1)
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

                       // .addMigrations(MIGRATION_3_4, MIGRATION_4_5)
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
//
//        // Migration from version 3 to 4
//        private val MIGRATION_3_4: Migration = object : Migration(3, 4) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("CREATE TABLE IF NOT EXISTS `reservation_new` (`id_reservation` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `id_place` INTEGER NOT NULL, `idParking` INTEGER NOT NULL, `date` TEXT NOT NULL, `heure_entree` TEXT NOT NULL, `heure_sortie` TEXT NOT NULL, `codeQr` TEXT NOT NULL, `prix` REAL NOT NULL, `username` TEXT NOT NULL)")
//                database.execSQL("INSERT INTO reservation_new (id_reservation, id_place, idParking, date, heure_entree, heure_sortie, codeQr, prix, username) SELECT id_reservation, id_place, idParking, date, heure_entree, heure_sortie, codeQr, prix, username FROM reservation")
//                database.execSQL("DROP TABLE reservation")
//                database.execSQL("ALTER TABLE reservation_new RENAME TO reservation")
//            }
//        }
//
//        private val MIGRATION_4_5: Migration = object : Migration(4, 5) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                // Step 1: Create new table with updated schema for place
//                database.execSQL("""
//            CREATE TABLE place_new (
//                id_place INTEGER PRIMARY KEY NOT NULL,
//                reservee INTEGER NOT NULL CHECK (reservee IN (0, 1)),
//                idParking TEXT NOT NULL
//            )
//        """.trimIndent())
//
//                // Step 2: Copy data from the old table to the new table
//                database.execSQL("""
//            INSERT INTO place_new (id_place, reservee, idParking)
//            SELECT id_place,
//                   CASE WHEN reservee = 1 THEN 1 ELSE 0 END,  -- Converting Integer to Boolean (1 for true, 0 for false)
//                   idParking
//            FROM place
//        """.trimIndent())
//
//                // Step 3: Drop the old table
//                database.execSQL("DROP TABLE place")
//
//                // Step 4: Rename the new table to the old table's name
//                database.execSQL("ALTER TABLE place_new RENAME TO place")
//
//                // Step 5: Create new table with updated schema for parking
//                database.execSQL("""
//            CREATE TABLE parking_new (
//                idParking INTEGER PRIMARY KEY NOT NULL,
//                name TEXT NOT NULL,
//                commune TEXT NOT NULL,
//                adresse TEXT,
//                nb_places INTEGER NOT NULL,
//                latitude REAL NOT NULL,
//                longitude REAL NOT NULL,
//                image TEXT NOT NULL,
//                tarif REAL NOT NULL
//            )
//        """.trimIndent())
//
//                // Step 6: Copy data from the old table to the new table, handling missing columns
//                try {
//                    database.execSQL("""
//                INSERT INTO parking_new (idParking, name, commune, adresse, nb_places, latitude, longitude, image, tarif)
//                SELECT idParking, name, commune, adresse, nb_places, latitude, longitude, image, tarif
//                FROM parking
//            """.trimIndent())
//                } catch (e: SQLiteException) {
//                    // In case the original table doesn't have the adresse column, handle it
//                    database.execSQL("""
//                INSERT INTO parking_new (idParking, name, commune, nb_places, latitude, longitude, image, tarif)
//                SELECT idParking, name, commune, nb_places, latitude, longitude, image, tarif
//                FROM parking
//            """.trimIndent())
//                }
//
//                // Step 7: Drop the old table
//                database.execSQL("DROP TABLE parking")
//
//                // Step 8: Rename the new table to the old table's name
//                database.execSQL("ALTER TABLE parking_new RENAME TO parking")
//            }
//        }

    }
}

