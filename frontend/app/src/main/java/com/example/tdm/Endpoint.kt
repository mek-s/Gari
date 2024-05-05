package com.example.tdm

import com.example.tdm.data.models.Parking
import com.example.tdm.data.models.Place
import com.example.tdm.data.models.Reservation
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Endpoint {
    @GET("parkings/all")
    suspend fun getAllParkings(): Response<List<Parking>>

    @GET("places/all")
    suspend fun getAllPlaces(): Response<List<Place>>
    @GET("reservations/all")
    suspend fun getAllResesrvations(): Response<List<Reservation>>


    companion object {
        var endpoint: Endpoint? = null
        fun createEndpoint(): Endpoint {
            if (endpoint == null) {
                endpoint = Retrofit.Builder().baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create()).build()
                    .create(Endpoint::class.java)
            }

            return endpoint!!
        }
    }
}