package com.example.tdm.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "place")
data class Place(
    @PrimaryKey
    val id_place: Int,
    val reservee: Boolean,
    val idParking: String
)