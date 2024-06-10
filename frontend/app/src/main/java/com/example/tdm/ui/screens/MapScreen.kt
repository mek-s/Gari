import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.util.Pair
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.tdm.R
import com.example.tdm.URL
import com.example.tdm.data.viewModels.ParkingModel
import com.example.tdm.ui.components.ParkingInfoCard
import com.example.tdm.ui.components.SearchBar
import com.example.tdm.ui.components.bitmapDescriptorFromVector
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerInfoWindow



@SuppressLint("MissingPermission")
private fun getLastUserLocation(
    context: Context,
    fusedLocationProviderClient: FusedLocationProviderClient,
    onGetLastLocationSuccess: (Pair<Double, Double>) -> Unit,
    onGetLastLocationFailed: (Exception) -> Unit
) {
    if (areLocationPermissionsGranted(context)) {
        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location ->
                location?.let {
                    onGetLastLocationSuccess(Pair(it.latitude, it.longitude))
                } ?: run {
                    onGetLastLocationFailed(Exception("Location is null"))
                }
            }
            .addOnFailureListener { exception ->
                onGetLastLocationFailed(exception)
            }
    } else {
        onGetLastLocationFailed(Exception("Location permissions not granted"))
    }
}

// Placeholder function to check location permissions
fun areLocationPermissionsGranted(context: Context): Boolean {
    val fineLocationPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
    val coarseLocationPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
    return fineLocationPermission == android.content.pm.PackageManager.PERMISSION_GRANTED &&
            coarseLocationPermission == android.content.pm.PackageManager.PERMISSION_GRANTED
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun DisplayMap(navController: NavHostController, parkingModel: ParkingModel) {


    var query by remember { mutableStateOf("") }
    val context = LocalContext.current
    val locationPermissionState = rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var userLocation by remember { mutableStateOf<LatLng?>(null) }
    var filteredParkings by remember { mutableStateOf(parkingModel.allRParkings.value) }


    LaunchedEffect(locationPermissionState.status.isGranted) {
        parkingModel.getAllParkings()

        if (areLocationPermissionsGranted(context)) {
            getLastUserLocation(
                context = context,
                fusedLocationProviderClient = fusedLocationClient,
                onGetLastLocationSuccess = { latLng ->
                    userLocation = LatLng(latLng.first, latLng.second)
                },
                onGetLastLocationFailed = { exception ->
                    // Handle the failure case (e.g., show a message to the user)
                    exception.printStackTrace()
                }
            )
        } else {
            locationPermissionState.launchPermissionRequest()
        }
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(userLocation ?: LatLng(36.721, 3.163), 10f)
    }


    Column {
        SearchBar(
            query = query,
            onQueryChanged = { newQuery ->
                query = newQuery
                filteredParkings = parkingModel.allRParkings.value?.filter {
                    it.name.contains(query, ignoreCase = true) ||
                            it.commune.contains(query, ignoreCase = true)
                }!!
            },
            onSearch = {
                filteredParkings = parkingModel.allRParkings.value?.filter {
                    it.name.contains(query, ignoreCase = true) ||
                            it.commune.contains(query, ignoreCase = true)
                }!!
            }
        )

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = MapProperties(isMyLocationEnabled = true , mapType = MapType.TERRAIN),
            cameraPositionState = cameraPositionState

        ) {
            val parkings = filteredParkings
            if (parkings != null) {
                for (parking in parkings) {
                    val position = LatLng(parking.latitude, parking.longitude)
                    val icon = bitmapDescriptorFromVector(
                        LocalContext.current, R.drawable.parking_sign
                    )
                    MarkerInfoWindow(
                        state = MarkerState(position = position),
                        icon = icon
                    ) { marker ->
                        ParkingInfoCard(
                            parking,
                            navController
                        )
                    }
                }
            }
        }
    }
}

