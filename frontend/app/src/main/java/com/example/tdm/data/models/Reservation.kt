package com.example.tdm.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "reservation")
data class Reservation(
    @PrimaryKey
    val id_reservation: Int,
    val id_place: Int,
    val date: String,
    val heure_entree: String,
    val heure_sortie: String,
    val code_qr: String,
    val prix: Double

)

