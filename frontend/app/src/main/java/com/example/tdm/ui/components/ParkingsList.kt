package com.example.tdm.ui.components


import Routes
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.tdm.URL
import com.example.tdm.data.models.Parking
import com.example.tdm.ui.theme.black
import com.example.tdm.ui.theme.darkBlue
import com.example.tdm.ui.theme.lightBlue
import com.example.tdm.ui.theme.lightGrey
import com.example.tdm.ui.theme.orange
import com.example.tdm.ui.theme.white


@Composable
fun ParkingsList( navController : NavHostController ,parkings: List<Parking>) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(15.dp)
    ) {
        Text(
            text = "Parking List", fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            fontSize = 20.sp, color = darkBlue
        )

        LazyColumn(Modifier.height(1000.dp)) {

            items(parkings) { parking ->
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .padding(4.dp)
                        .clickable {
                            navController.navigate(Routes.ParkingDetails.createRoute(parking.idParking))
                        }
                ) {
                    Column(

                        modifier = Modifier.weight(2f)
                    ) {
                        AsyncImage(
                            model = URL + parking.image,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(150.dp)
                                .aspectRatio(1f)
                                .padding(8.dp)
                                .clip(RoundedCornerShape(25.dp))

                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.weight(2f)
                    ){
                        Text(
                            text = parking.name, fontWeight = FontWeight.Bold,
                            fontSize = 20.sp, color = black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row (
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(
                                imageVector = Icons.Outlined.LocationOn,
                                contentDescription = "Map Icon",
                                tint = lightGrey
                            )
                            Text(
                                text = parking.commune,
                                fontSize = 11.sp, color = lightGrey
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row {


                        Text(text = parking.tarif.toString() + " DA" , fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif, color = orange)
                        Text(text = "/ hr", color = lightGrey)
                    }
                    }
                }
            }

        }
    }

}
