package com.example.tdm.ui.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tdm.data.models.Reservation
import com.example.tdm.ui.theme.black
import com.example.tdm.ui.theme.darkBlue
import com.example.tdm.ui.theme.lightGrey



@Composable
fun ReservationList(navController: NavHostController, reservations: List<Reservation>) {
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
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .padding(4.dp)
                        .clickable {
                            navController.navigate(Routes.ReservationDetails.createRoute(reservation.id_reservation, reservation.idParking))
                        }
                ) {
                    Column(
                        modifier = Modifier.weight(2f)
                    ) {
                        Text(
                            text = "Reservation ID: ${reservation.id_reservation}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = black
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Date: ${reservation.date}",
                            fontSize = 14.sp,
                            color = lightGrey
                        )
                        Text(
                            text = "Place ID: ${reservation.id_place}",
                            fontSize = 14.sp,
                            color = lightGrey
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.weight(1f)
                    ) {
                        // Add any additional content here, if needed
                    }
                }
            }
        }
    }
}
