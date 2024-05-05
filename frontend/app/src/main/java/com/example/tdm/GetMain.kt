package com.example.tdm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.tdm.data.viewModels.ParkingModel


@Composable
fun GetMain(parkingModel: ParkingModel) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
//            val parking = Parking(
//                name = "Parking 1",
//                commune = "Commune 1",
//                nbPlaces = 10,
//                image = "",
//                latitude = 120.097,
//                longitude = 111.992,
//                tarif = 200.0
//            )

//            parkingModel.saveParking(parking)

        }) {

            Text(text = "Add Parking")
        }

        Button(onClick = {
            parkingModel.getAllParkings()
        }) {

            Text(text = "Get Count")
        }

        Text(text = parkingModel.allRParkings.value.size.toString())



    }

//    val singapore = LatLng(1.35, 103.87)
//    val cameraPositionState = rememberCameraPositionState {
//        position = CameraPosition.fromLatLngZoom(singapore, 10f)
//    }
//    GoogleMap(
//        modifier = Modifier.fillMaxSize(),
//        cameraPositionState = cameraPositionState
//    )
}
