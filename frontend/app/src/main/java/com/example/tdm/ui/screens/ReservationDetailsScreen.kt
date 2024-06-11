import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberImagePainter
import com.example.tdm.data.viewModels.ParkingModel
import com.example.tdm.ui.theme.darkBlue
import com.lightspark.composeqr.DotShape
import com.lightspark.composeqr.QrCodeColors
import com.lightspark.composeqr.QrCodeView

@Composable
fun ReservationDetails(
    reservationId: Int,
    viewModelReserv: ReservationModel,
    viewModelParking: ParkingModel
) {
    val reservation = viewModelReserv.reservation.value
    val parking = viewModelParking.parking.value

    LaunchedEffect(reservationId) {
        viewModelReserv.getReservationById(reservationId)
    }

    LaunchedEffect(reservation?.idParking) {
        reservation?.idParking?.let { parkingId ->
            viewModelParking.getParkingById(parkingId)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding(16.dp)
    ) {
        reservation?.let { res ->
            Text(text = "Reservation #: ${res.id_reservation}", fontSize = 18.sp)
            Text(text = "Date: ${res.date}", fontSize = 18.sp)
            Text(text = "Heure Entree: ${res.heure_entree}", fontSize = 18.sp)
            Text(text = "Heure Sortie: ${res.heure_sortie}", fontSize = 18.sp)
            Text(text = "Prix: ${res.prix}", fontSize = 18.sp)
            parking?.let { park ->
                Text(text = "Nom Parking: ${park.name}", fontSize = 18.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = rememberImagePainter(park.image),
                    contentDescription = "Parking Image",
                    modifier = Modifier.size(200.dp)
                )
            }
            res.codeQr?.let { qrCodeData ->
                DisplayQRCode(data = qrCodeData)
            }
        }
    }
}


