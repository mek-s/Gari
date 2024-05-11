package com.example.tdm.ui.components

import androidx.compose.runtime.Composable
import com.example.tdm.data.models.Parking

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.tdm.URL

@Composable
fun ParkingDetails(parking: Parking?) {
    if (parking != null) {


        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(bottom = 10.dp)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .zIndex(1f)
                ) {
                    AsyncImage(
                        model = URL + parking.image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .padding(8.dp)
                    )

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = parking.name,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Row {
                Text(text = parking.commune)
                Text(text = parking.tarif.toString())
                Text(text = parking.nbPlaces.toString())
            }
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(1f)
            ) {
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(Color.LightGray),
                    shape = RoundedCornerShape(0.dp),
                    modifier = Modifier.padding(end = 10.dp)
                ) { Text(text = "Book a place") }

            }
        }
    }
}