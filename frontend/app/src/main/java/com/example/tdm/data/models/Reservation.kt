package com.example.tdm.data.models

import androidx.annotation.StringDef
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "reservation")
data class Reservation(
    @PrimaryKey(autoGenerate = true)
    val id_reservation: Int,
    val id_place: Int,
    val idParking: Int,
    val date: String,
    val heure_entree: String,
    val heure_sortie: String,
    val codeQr: String,
    val prix: Double,
    val username: String
)
