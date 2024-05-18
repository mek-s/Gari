package com.example.tdm.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "place")
data class Place(
    @PrimaryKey
    val idPlace: String,
    val reservee: Integer,
    val idParking: String
)