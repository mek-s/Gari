import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tdm.R
import com.example.tdm.data.models.Reservation
import com.example.tdm.data.viewModels.ParkingModel
import com.example.tdm.data.viewModels.PlaceModel
import com.lightspark.composeqr.DotShape
import com.lightspark.composeqr.QrCodeColors
import com.lightspark.composeqr.QrCodeView
import java.util.*

@Composable
fun DisplayReservation(
    parkingId: Int,
    isLoggedIn: Boolean,
    username: String,
    viewModelReserv: ReservationModel,
    viewModelPark: ParkingModel,
    viewModelPlac: PlaceModel,
    navController: NavController
) {
    if (isLoggedIn) {
        var date by remember { mutableStateOf("") }
        var heureEntr by remember { mutableStateOf("") }
        var heureSort by remember { mutableStateOf("") }
        var codeQrr by remember { mutableStateOf("") }
        var parkingTariff by remember { mutableStateOf(0.0) }
        var randomPlaceId by remember { mutableStateOf<Int?>(null) }
        var reservation by remember { mutableStateOf<Reservation?>(null) }
        var showDialog by remember { mutableStateOf(false) }

        val context = LocalContext.current

        val crt = viewModelReserv.created
        if (crt == 1 ) {
            LaunchedEffect(Unit ){
                Toast.makeText(
                    context,
                    "Reservation created successfully",
                    Toast.LENGTH_SHORT
                ).show()
                showDialog = true

            }

        }

        // Fetch parking tariff by ID
        LaunchedEffect(parkingId) {
            if (parkingId != null) {
                viewModelPark.getParkingTariffById(parkingId) { tariff ->
                    parkingTariff = tariff ?: 0.0
                }
            }
        }

        // Function to fetch a random place ID
        fun fetchRandomPlaceId(parkingId: Int) {
            viewModelPlac.randomlyAvailablePlace(parkingId) { placeId ->
                randomPlaceId = placeId
            }
        }

        // Fetch randomly available place when the composable is first drawn
        LaunchedEffect(parkingId) {
            if (parkingId != null) {
                fetchRandomPlaceId(parkingId)
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp)
                .height(400.dp)
        ) {
            DateDisplay(onDateSelected = { selectedDate ->
                date = selectedDate
            })
            Spacer(modifier = Modifier.height(16.dp))
            TimeDisplay(onTimeSelected = { selectedTime ->
                heureEntr = selectedTime
            })
            Spacer(modifier = Modifier.height(16.dp))
            TimeDisplay(onTimeSelected = { selectedTime ->
                heureSort = selectedTime
            })

            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    if (date.isNotBlank() && heureEntr.isNotBlank() && heureSort.isNotBlank() && randomPlaceId != null && parkingId != null) {
                        val prixValue = calculatePrice(heureEntr, heureSort, parkingTariff)
                        val newReservation = Reservation(
                            id_reservation = 0,
                            id_place = randomPlaceId!!,
                            idParking = parkingId,
                            date = date,
                            heure_entree = heureEntr,
                            heure_sortie = heureSort,
                            codeQr = "",
                            prix = prixValue,
                            username = username
                        )

                        viewModelReserv.createReservation(
                            idParking = parkingId,
                            idPlace = randomPlaceId!!,
                            date = date,
                            heureEntree = heureEntr,
                            heureSortie = heureSort,
                            prix = prixValue,
                            codeQr = "${newReservation.id_place}:${newReservation.date}:${newReservation.prix}", // Placeholder, will be set later
                            username = username
                        )
                        codeQrr = "${newReservation.id_place}:${newReservation.date}:${newReservation.prix}"
                        reservation = newReservation
                        showDialog = true
                    }
                },
                enabled = date.isNotBlank() && heureEntr.isNotBlank() && heureSort.isNotBlank() && randomPlaceId != null
            ) {
                Text(text = "Book Parking")
            }

//            if (reservation != null) {
//                DisplayQRCode(reservation!!.codeQr)
//            }


        }

        // Dialog box to display reservation details
        if (showDialog ) {
            ReservationConfirmationDialog(reservation = reservation!!,codeQr = codeQrr, onDismiss = { showDialog = false })
        }
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.noo), // Replace with your image resource
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
                    navController.navigate(Routes.Login.route)
                },
                colors = ButtonDefaults.buttonColors(Color(0xFFA0D7FC)),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Sign In")
            }
        }
    }
}

@Composable
fun DisplayQRCode(data: String) {
    Box(
        modifier = Modifier
            .size(200.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        QrCodeView(
            data = data,
            modifier = Modifier.size(200.dp),
            colors = QrCodeColors(
                background = Color.White,
                foreground = Color.Black
            ),
            dotShape = DotShape.Square,
        )
    }
}



@Composable
fun ReservationConfirmationDialog(reservation: Reservation, codeQr: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Reservation Confirmation",
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        },
        text = {
            Column {
                Text("Parking ID: ${reservation.id_place}")
                Text("Username: ${reservation.username}")
                DisplayQRCode(codeQr)
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss
            ) {
                Text("Close")
            }
        }
    )
}







fun calculatePrice(heureEntree: String, heureSortie: String, parkingTariff: Double): Double {
    val (hourIn, minIn) = heureEntree.split(":").map { it.toInt() }
    val (hourOut, minOut) = heureSortie.split(":").map { it.toInt() }

    val totalTime = (hourOut - hourIn) + (minOut - minIn) / 60.0
    return totalTime * parkingTariff
}

@Composable
fun DateDisplay(onDateSelected: (String) -> Unit) {
    val mContext = LocalContext.current
    val mCalendar = Calendar.getInstance()
    val mYear = mCalendar.get(Calendar.YEAR)
    val mMonth = mCalendar.get(Calendar.MONTH)
    val mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()
    val mDate = remember { mutableStateOf("") }
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            // Format the date and pass it to the callback
            val formattedDate = formatDate(year, month + 1, dayOfMonth)
            mDate.value = formattedDate
            onDateSelected(mDate.value)
        }, mYear, mMonth, mDay
    )

    Column {
        Button(onClick = {
            mDatePickerDialog.show()
        }) {
            Text(text = "Open Date Picker", color = Color.White)
        }
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "Selected Date: ${mDate.value}",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

fun formatDate(year: Int, month: Int, day: Int): String {
    return "$year-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}"
}

@Composable
fun TimeDisplay(onTimeSelected: (String) -> Unit) {
    val mContext = LocalContext.current
    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]
    val mTime = remember { mutableStateOf("") }

    val mTimePickerDialog = TimePickerDialog(
        mContext,
        { _, mHour: Int, mMinute: Int ->
            mTime.value = "$mHour:$mMinute"
            onTimeSelected(mTime.value) // Call the callback function with the selected time
        }, mHour, mMinute, false
    )

    Column {

        Button(onClick = { mTimePickerDialog.show() }) {
            Text(text = "Open Time Picker", color = Color.White)
        }
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "Selected Time: ${mTime.value}",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
