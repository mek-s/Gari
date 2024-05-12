import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.tdm.data.viewModels.ParkingModel
import com.example.tdm.ui.components.ParkingDetails
import com.example.tdm.ui.components.ParkingsList

@Composable
fun DisplayHome(navController : NavHostController, parkingModel: ParkingModel) {

    parkingModel.getAllParkings()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DisplayLoading(loading = parkingModel.loading.value)

        ParkingsList(navController,parkings = parkingModel.allRParkings.value)
        DisplayErrorMessage(parkingModel.displayMsg.value)
    }
}

@Composable
fun DisplayParkingDetails(navController : NavHostController, parkingModel: ParkingModel , parkingId : Int?){
   if (parkingId != null) parkingModel.getParkingById(parkingId)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
    ParkingDetails(navController ,parkingModel.parking.value)
    }
}

@Composable
fun DisplayLoading(loading: Boolean) {
    if (loading == true) {
        CircularProgressIndicator()
    }

}

@Composable
fun DisplayErrorMessage(display: Boolean) {
    val context = LocalContext.current
    if (display) {
        Toast.makeText(context, "An arror happened", Toast.LENGTH_SHORT).show()
    }
}