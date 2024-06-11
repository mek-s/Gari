package com.example.tdm.ui.components

import Routes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.tdm.URL
import com.example.tdm.data.models.Parking
import com.example.tdm.ui.theme.black
import com.example.tdm.ui.theme.darkBlue
import com.example.tdm.ui.theme.lightGrey
import com.example.tdm.ui.theme.orange

@Composable
fun ParkingsList(navController: NavHostController, parkings: List<Parking>) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .respectCacheHeaders(false).build()

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(15.dp)
    ) {
        Text(
            text = "Bookmark Parking Place", fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            fontSize = 20.sp, color = darkBlue
        )

        LazyColumn(Modifier.height(1000.dp)) {
            items(parkings) { parking ->
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .padding(10.dp)
                        .fillMaxWidth()
                        .background(Color.White)
                        .clickable {
                            navController.navigate(Routes.ParkingDetails.createRoute(parking.idParking))
                        }
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(URL + parking.image)
                                .diskCachePolicy(CachePolicy.ENABLED)
                                .memoryCachePolicy(CachePolicy.ENABLED)
                                .build(), imageLoader
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(100.dp)
                            .aspectRatio(1f)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                    ) {
                        Text(
                            text = parking.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
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
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            repeat(5) {
                                Icon(
                                    imageVector = Icons.Outlined.Star,
                                    contentDescription = "Star Icon",
                                    tint = Color.Yellow,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                            Text(
                                text = "(128)",
                                fontSize = 12.sp,
                                color = lightGrey,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))

                    }

                        Text(
                            text = parking.tarif.toString()+" DA/H",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = orange
                        )


                }
            }
        }
    }
}
