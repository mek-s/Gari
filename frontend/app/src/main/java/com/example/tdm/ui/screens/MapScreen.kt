//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavHostController
//import com.example.tdm.data.viewModels.ParkingModel
//import com.google.android.gms.maps.model.CameraPosition
//import com.google.android.gms.maps.model.LatLng
//import com.google.maps.android.compose.GoogleMap
//import com.google.maps.android.compose.rememberCameraPositionState
//import java.lang.reflect.Modifier
//
//@Composable
//fun DisplayMap(navController : NavHostController, parkingModel: ParkingModel){
//    val singapore = LatLng(1.35, 103.87)
//    val cameraPositionState = rememberCameraPositionState {
//        position = CameraPosition.fromLatLngZoom(singapore, 10f)
//    }
//    GoogleMap(
//        modifier = Modifier.fillMaxSize(),
//        cameraPositionState = cameraPositionState
//    )
//}
