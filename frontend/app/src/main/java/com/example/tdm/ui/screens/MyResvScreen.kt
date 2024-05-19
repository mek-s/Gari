import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tdm.data.models.Reservation
import com.example.tdm.data.viewModels.ParkingModel
import com.example.tdm.ui.components.ParkingsList
import com.example.tdm.ui.components.ReservationList




@Composable
 fun DisplayMyReservations (
    isLoggedIn: Boolean,
    username : String,
    viewModel: AuthViewModel,
    viewModelReserv: ReservationModel,
    navHostController: NavHostController,

) {
    if ( isLoggedIn) {
        viewModelReserv.getReservationsByUsername(username)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ReservationList(navController = navHostController, reservations = viewModelReserv.allR.value)


        }


    }
    else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = "Please log in to view ur reservations.",
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