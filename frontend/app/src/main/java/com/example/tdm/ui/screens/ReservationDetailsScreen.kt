import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.tdm.data.models.Parking
import com.example.tdm.data.viewModels.ParkingModel
import com.example.tdm.ui.theme.blueBackground
import com.example.tdm.ui.theme.darkBlue
import com.example.tdm.ui.theme.lightGrey
import com.example.tdm.ui.theme.orange
import com.lightspark.composeqr.DotShape
import com.lightspark.composeqr.QrCodeColors
import com.lightspark.composeqr.QrCodeView

@Composable
fun ReservationDetails(
    reservationId: Int,
    viewModelReserv: ReservationModel,
    viewModelParking: ParkingModel,
    navController: NavController
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
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(enabled = true, state = ScrollState(0))
            .padding(10.dp)
            .fillMaxSize()
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            // Back arrow icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(blueBackground)
                    .clickable {
                        navController.navigate(Routes.MyReserv.route)
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "Arrow Icon",
                    modifier = Modifier.size(48.dp * 0.6f), // Adjust the size of the icon
                    tint = darkBlue
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = "Reservation Details", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }

        Text(
            text = "Parking Infos",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )
        if (parking != null) {
            Text(
                text = parking.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )

           Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 4.dp)
          ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "Location Icon",
                    tint = darkBlue,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = parking.commune +" , "+parking.adresse,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = lightGrey
                )
                Spacer(modifier = Modifier.width(100.dp))
                Text(
                    text = parking.tarif.toString()+" DA/H",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = orange
                )
            }
        }

Column(
    horizontalAlignment = Alignment.CenterHorizontally
) {


    reservation?.let { res ->
        Text(text = "Reservation #: ${res.id_reservation}", fontSize = 18.sp)
        Text(text = "Date: ${res.date}", fontSize = 18.sp)
        Text(text = "Heure Entree: ${res.heure_entree}", fontSize = 18.sp)
        Text(text = "Heure Sortie: ${res.heure_sortie}", fontSize = 18.sp)
        Text(text = "Prix: ${res.prix}", fontSize = 18.sp)
        res.codeQr?.let { qrCodeData ->
            DisplayQRCode(data = qrCodeData)
        }
    }
}


    }
}


