import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tdm.ui.components.ReservationList

import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.tdm.R
import com.example.tdm.data.viewModels.ParkingModel


@Composable
fun DisplayMyReservations(
    isLoggedIn: Boolean,
    username: String,
    viewModelReserv: ReservationModel,
    parkingModel : ParkingModel,
    navHostController: NavHostController
) {
    if (isLoggedIn) {
        viewModelReserv.getReservationsByUsername(username)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (viewModelReserv.allReservationss.value.isNotEmpty()) {
                ReservationList(
                    navController = navHostController,
                    reservations = viewModelReserv.allReservationss.value,
                    allParkings = parkingModel.allRParkings.value
                )

            } else {
                Text(
                    text = "No reservations found.",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.noo),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp) // Adjust size as needed
                    .padding(bottom = 16.dp)
            )
            Text(
                text = "You are not authenticated",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Button(
                onClick = {
                    navHostController.navigate(Routes.Login.route)
                },
                colors = ButtonDefaults.buttonColors(Color(0xFFFF8264)),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Sign In")
            }
        }
    }
}
