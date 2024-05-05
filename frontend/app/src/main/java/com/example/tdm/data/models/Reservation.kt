package com.example.tdm.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "reservation")
data class Reservation(
    @PrimaryKey
    val idReservation: String,
    val idPlace: String, // Assuming FK stands for Foreign Key
    val date: Date,
    val heureEntree: String,
    val heureSortie: String,
    val codeQR: String,
    val prix: Double,
    val validite: Boolean
)
