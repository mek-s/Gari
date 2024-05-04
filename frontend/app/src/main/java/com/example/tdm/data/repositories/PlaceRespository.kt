package com.example.tdm.data.repositories

import com.example.tdm.data.dao.PlaceDao
import com.example.tdm.data.models.Place


class PlaceRespository(private  val placeDao: PlaceDao) {
    fun getAllPlaces(): List<Place> = placeDao.getAllPlaces()
}