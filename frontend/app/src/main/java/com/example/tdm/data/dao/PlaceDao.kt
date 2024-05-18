package com.example.tdm.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.tdm.data.models.Place
import com.google.android.gms.common.internal.safeparcel.SafeParcelable

@Dao
interface PlaceDao {
    @Query("SELECT * FROM place")
     fun getAllPlaces(): List<Place>


    @Query("SELECT * FROM place WHERE reservee = 0 AND id_parking = :parkingId ORDER BY RANDOM() LIMIT 1")
    fun getRandomUnreservedPlaceForParking( parkingId: Int): Place?



    @Query("UPDATE place SET reservee = 1 WHERE id_place = :id")
    suspend fun reservePlace(id: Int)


}