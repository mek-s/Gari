package com.example.tdm.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val  username: String,
    val  password: Boolean,
    val idParking: String
)