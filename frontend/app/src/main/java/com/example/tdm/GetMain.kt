package com.example.tdm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.tdm.data.dataModels.ReservationModel
import java.util.Date

@Composable
fun GetMain(reservationModel: ReservationModel) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Button(onClick = {
            reservationModel.getAllReservations()
        }) {

            Text(text = "Get Count")
        }

        Text(text = reservationModel.allReservations.value.size.toString())


    }
}
