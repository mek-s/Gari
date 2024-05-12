package com.example.tdm.ui.components

import androidx.compose.runtime.Composable
import com.example.tdm.data.models.Parking

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.tdm.URL
import com.example.tdm.ui.theme.black
import com.example.tdm.ui.theme.blueBackground
import com.example.tdm.ui.theme.darkBlue
import com.example.tdm.ui.theme.lightBlue
import com.example.tdm.ui.theme.lightGrey
@Composable
fun ParkingDetails(navController: NavHostController, parking: Parking?) {
    if (parking != null) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
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
                            navController.navigate(Routes.Home.route)
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
                // Parking name
                Text(
                    text = parking.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            // Parking image and location
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                // Parking image
                AsyncImage(
                    model = URL + parking.image,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(25.dp))
                )
                // Location icon and text
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = "Map Icon",
                        tint = lightGrey
                    )
                    Text(
                        text = parking.commune,
                        fontSize = 11.sp,
                        color = lightGrey,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                    Text(text = "Welcome to " + parking.name + "located at " + parking.commune+"our facility offers" +parking.nbPlaces + "well-lit, spacious parking spaces suitable for cars, motorcycles, and bicycles. With 24/7 surveillance and on-site staff, rest assured that your vehicle is in safe hands. ")
                }
            }

            // Book button
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(Color.LightGray),
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .padding(top = 50.dp)
                    .align(Alignment.End)
            ) {
                Text(text = "Book a place")
            }
        }
    }
}
