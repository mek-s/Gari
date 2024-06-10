import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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


@Composable
fun DisplayMyReservations(
    isLoggedIn: Boolean,
    username: String,
    viewModelReserv: ReservationModel,
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
                    reservations = viewModelReserv.allReservationss.value
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
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Please log in to view your reservations.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Button(
                onClick = { navHostController.navigate(Routes.Login.route) },
                colors = ButtonDefaults.buttonColors(Color(0xFFFF5722)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Text("Log In")
            }
        }
    }
}
