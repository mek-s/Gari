import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.tdm.data.viewModels.ParkingModel
import com.example.tdm.ui.components.ParkingDetails
import com.example.tdm.ui.components.ParkingsList
import androidx.navigation.NavHostController
import com.example.tdm.data.models.Parking
import com.example.tdm.ui.components.FilterBar
import com.example.tdm.ui.components.SearchBar

@Composable
fun DisplayHome(navController : NavHostController, parkingModel: ParkingModel) {


    var query by remember { mutableStateOf("") }
    var selectedCommune by remember { mutableStateOf("") }
    var filteredParkings by remember { mutableStateOf(parkingModel.allRParkings.value) }

    LaunchedEffect(parkingModel.allRParkings.value) {
        parkingModel.getAllParkings()
        filteredParkings = parkingModel.allRParkings.value
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        SearchBar(
            query = query,
            onQueryChanged = { newQuery ->
                query = newQuery
                filteredParkings = filterAndSearchParkings(query, selectedCommune, parkingModel)
            },
            onSearch = {
                filteredParkings = filterAndSearchParkings(query, selectedCommune, parkingModel)
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        FilterBar(
            selectedCommune = selectedCommune,
            onCommuneChanged = { newCommune ->
                selectedCommune = newCommune
                filteredParkings = filterAndSearchParkings(query, selectedCommune, parkingModel)
            },
            parkings = parkingModel.allRParkings.value
        )

        DisplayLoading(loading = parkingModel.loading.value)
        ParkingsList(navController, parkings = filteredParkings ?: listOf())
        DisplayErrorMessage(parkingModel.displayMsg.value)
    }
}

fun filterAndSearchParkings(
    query: String,
    selectedCommune: String,
    parkingModel: ParkingModel
): List<Parking> {
    return parkingModel.allRParkings.value?.filter {
        (it.name.contains(query, ignoreCase = true) ||
                it.commune.contains(query, ignoreCase = true)) &&
                (selectedCommune.isEmpty() || it.commune == selectedCommune)
    } ?: listOf()
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