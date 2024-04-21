package com.example.tdm.data.models


data class Parking(
    val idParking: String,
    val name: String,
    val commune: String,
    val nbPlaces: Int,
    val latitude: Double,
    val longitude: Double,
    val tarif: Double
)

