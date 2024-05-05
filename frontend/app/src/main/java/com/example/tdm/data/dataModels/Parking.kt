package com.example.tdm.data.dataModels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "parking")
data class Parking(
    @PrimaryKey
    val idParking: String,
    val name: String,
    val commune: String,
    val nbPlaces: Int,
    val latitude: Double,
    val longitude: Double,
    val tarif: Double
)

