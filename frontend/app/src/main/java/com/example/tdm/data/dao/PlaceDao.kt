package com.example.tdm.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.tdm.data.models.Place

@Dao
interface PlaceDao {
    @Query("SELECT * FROM place")
     fun getAllPlaces(): List<Place>

}