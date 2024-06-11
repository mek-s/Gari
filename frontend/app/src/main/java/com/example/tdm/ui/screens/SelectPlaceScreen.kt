package com.example.tdm.ui.screens


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tdm.data.models.Place
import com.example.tdm.data.viewModels.PlaceModel
import com.example.tdm.ui.theme.blueBackground
import com.example.tdm.ui.theme.darkBlue

@Composable
fun SelectPlaceScreen(parkingId: Int, navController: NavHostController, viewModel: PlaceModel) {
    var availablePlaces by remember { mutableStateOf(listOf<Place>()) }

    LaunchedEffect(parkingId) {
        viewModel.getAvailablePlaces(parkingId) { places ->
            if (places != null) {
                availablePlaces = places
            }
        }
    }
if (availablePlaces.isNotEmpty()){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            // Back arrow icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(blueBackground)
                    .clickable {
                        navController.navigate(Routes.ParkingDetails.createRoute(parkingId))
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "Arrow Icon",
                    modifier = Modifier.size(48.dp * 0.6f), // Adjust the size of the icon
                    tint = darkBlue
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Select a Place",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

        }



        for (place in availablePlaces) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        navController.navigate(Routes.Reserv.createRoute(parkingId, place.id_place))
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Place ID: ${place.id_place}",
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                if (place.reservee) {
                    Text(
                        text = "Reserved",
                        fontSize = 16.sp,
                        color = Color.Red
                    )
                } else {
                    Text(
                        text = "Available",
                        fontSize = 16.sp,
                        color = Color.Green
                    )
                }
            }
        }
    }
}else{
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
         Text(text = "No Available places for the moment , try later !")
    }
}

}
