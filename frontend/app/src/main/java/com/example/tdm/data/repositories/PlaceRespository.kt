package com.example.tdm.data.repositories

import Place
import PlaceDao

class PlaceRespository(private  val placeDao: PlaceDao) {
    fun getAllPlaces(): List<Place> = placeDao.getAllPlaces()
}