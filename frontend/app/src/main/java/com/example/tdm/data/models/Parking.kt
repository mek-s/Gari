package com.example.tdm.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "parking")
data class Parking(
    @PrimaryKey
    val idParking: Int,
    val name: String,
    val commune: String,
    val nb_places: Int,
    val latitude: Double,
    val longitude: Double,
    val image : String,
    val tarif: Double
)

