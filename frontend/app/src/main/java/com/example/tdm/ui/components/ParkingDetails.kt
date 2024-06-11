package com.example.tdm.ui.components

import androidx.compose.runtime.Composable
import com.example.tdm.data.models.Parking

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.rounded.Star
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.tdm.R
import com.example.tdm.URL
import com.example.tdm.ui.theme.black
import com.example.tdm.ui.theme.blueBackground
import com.example.tdm.ui.theme.darkBlue
import com.example.tdm.ui.theme.lightBlue
import com.example.tdm.ui.theme.lightGrey
import com.example.tdm.ui.theme.orange

@Composable
fun ParkingDetails(navController: NavHostController, parking: Parking?) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .respectCacheHeaders(false).build()
    val imageRequest =  ImageRequest.Builder(LocalContext.current)
        .data(URL + (parking?.image ?: ""))
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()

    if (parking != null) {
        Column(
            modifier = Modifier
                .verticalScroll(enabled = true, state = ScrollState(0))
                .padding(10.dp)
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
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = "Parking Details", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.LightGray)
            ) {
                AsyncImage(
                    model = imageRequest,
                    contentDescription = "Parking Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Text(
                text = parking.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "Location Icon",
                    tint = darkBlue,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = parking.commune +" , "+parking.adresse,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = lightGrey
                )
                Spacer(modifier = Modifier.width(100.dp))
                Text(
                    text = parking.tarif.toString()+" DA/H",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = orange
                )
            }

            // Review Stars
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                repeat(5) { index ->
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = "Star Icon",
                        tint = if (index < 5) Color.Yellow else Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "3.5",
                    fontSize = 16.sp,
                    color = lightGrey
                )
            }

            Text(
                text = "CC TV, Hydraulic Parking, Power Charging System, Security, Automated Ticket, Parking Assistant",
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {

                Button(
                    onClick = {
                        navController.navigate(Routes.Reserv.createRoute(parking.idParking))
                    },
                    colors = ButtonDefaults.buttonColors(darkBlue),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.height(48.dp)
                ) {
                    Text(text = "Book a place")
                }
            }
        }
    }
    else {


        Button(
            onClick = {

            },

            ) {
            Text(text = "aaaaaaaaaa")
        }
    }
}
