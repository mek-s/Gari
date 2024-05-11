import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tdm.Endpoint
import com.example.tdm.data.database
import com.example.tdm.data.models.Parking
import com.example.tdm.data.repositories.ParkingRepository
import com.example.tdm.data.viewModels.ParkingModel
import com.example.tdm.ui.components.ParkingsList

@Composable
fun DisplayHome(parkingRepository: ParkingRepository) {
    val parkingModel: ParkingModel = viewModel(factory = ParkingModel.Factory(parkingRepository))
   // parkingModel.getAllParkings()

    ParkingsList(parkings = parkingModel.allRParkings.value)
}