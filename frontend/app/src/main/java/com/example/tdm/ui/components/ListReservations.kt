package com.example.tdm.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tdm.data.models.Parking
import com.example.tdm.data.models.Reservation
import com.example.tdm.ui.theme.black
import com.example.tdm.ui.theme.darkBlue
import com.example.tdm.ui.theme.lightGrey
import com.example.tdm.ui.theme.orange


@Composable
fun ReservationList(navController: NavHostController, reservations: List<Reservation> , allParkings : List<Parking>) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(15.dp),
    ) {
        Text(
            text = "Reservation List",
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            fontSize = 20.sp,
            color = darkBlue
        )
        Spacer(Modifier.width(10.dp))

        LazyColumn(Modifier.height(1000.dp)) {
            items(reservations) { reservation ->

                val parking = allParkings.find { it.idParking == reservation.idParking }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .padding(10.dp)
                        .fillMaxWidth()
                        .background(Color.White)
                        .clickable {
                            navController.navigate(
                                Routes.ReservationDetails.createRoute(
                                    reservation.id_reservation,
                                    reservation.idParking
                                )
                            )
                        }
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                    ) {
                        if (parking != null) {
                            Text(
                                text = parking.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = black
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Outlined.LocationOn,
                                    contentDescription = "Location Icon",
                                    tint = lightGrey,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = parking.commune +" , "+parking.adresse,
                                    fontSize = 12.sp,
                                    color = lightGrey,
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Date: ${reservation.date}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = lightGrey
                        )
                        Text(
                            text = "Place : ${reservation.id_place}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    }
                    Text(
                        text = reservation.prix.toString()+" DA",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = orange
                    )
                }
            }
        }
    }
}
