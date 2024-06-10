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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.tdm.URL
import com.example.tdm.ui.theme.black
import com.example.tdm.ui.theme.blueBackground
import com.example.tdm.ui.theme.darkBlue
import com.example.tdm.ui.theme.lightBlue
import com.example.tdm.ui.theme.lightGrey
@Composable
fun ParkingDetails(navController: NavHostController, parking: Parking?) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .respectCacheHeaders(false).build()
    val imageRequest =  ImageRequest.Builder(LocalContext.current)
        .data(URL+ (parking?.image ?: ""))
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()

    if (parking != null) {
        Column(

            modifier = Modifier
                .verticalScroll(enabled = true, state = ScrollState(0))
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

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {


                    Image(
                        painter =
                        rememberAsyncImagePainter(imageRequest,imageLoader),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .height(500.dp)
                            .fillMaxWidth()
                            .padding(8.dp)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(25.dp))
                    )

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = parking.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = "Map Icon",
                        tint = darkBlue
                    )
                    Text(
                        text = parking.commune,
                        fontSize = 11.sp,
                        color = lightGrey,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }

            Row {
                Text(text = "Singapour, une cité-État insulaire située au large de la péninsule malaise, est un carrefour multiculturel dynamique. Son économie florissante, son infrastructure moderne et sa population diversifiée, composée de communautés chinoises, malaises, indiennes et eurasiennes, lui confèrent une identité unique. La ville offre un mélange harmonieux de gratte-ciel futuristes, de temples majestueux, de quartiers historiques et de parcs luxuriants. ")
            }
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(1f)
            ) {
                Button(
                    onClick = {
                        navController.navigate(Routes.Reserv.createRoute(parking.idParking))

                    },
                    colors = ButtonDefaults.buttonColors(darkBlue),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(top = 50.dp)

                ) {
                    Text(text = "Book a place")
                }
            }
        }


    }
}
