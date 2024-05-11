package com.example.tdm.ui.components


import Routes
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.tdm.data.models.Parking


@Composable
fun ParkingsList( navController : NavHostController ,parkings: List<Parking>) {
    LazyColumn(Modifier.height(1000.dp)) {
        items(parkings) { parking ->
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .padding(4.dp)
                    .background(Color(0xFFE0E0E0))
                    .clickable {
                        navController.navigate(Routes.ParkingDetails.createRoute(parking.idParking))
                    }
            ) {
                Column(
                    modifier = Modifier.weight(2f)
                ) {
                    Text(
                        text = parking.name, fontWeight = FontWeight.Bold,
                        fontSize = 12.sp, color = Color(0xFFB125EA)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = parking.commune,
                        fontSize = 11.sp, color = Color(0xFFB125BA)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Nombre de places  :" + parking.nbPlaces)
                }
            }
        }

    }
}
