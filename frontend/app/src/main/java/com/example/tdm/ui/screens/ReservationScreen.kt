import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tdm.data.models.Reservation
import com.example.tdm.data.viewModels.ParkingModel
import com.example.tdm.data.viewModels.PlaceModel
import java.util.*

@Composable
fun DisplayReservation(
    parkingId: Int?,
    viewModelReserv: ReservationModel,
    viewModelPark: ParkingModel,
    viewModelPlac: PlaceModel
) {
    var date by remember { mutableStateOf("") }
    var heureEntr by remember { mutableStateOf("") }
    var heureSort by remember { mutableStateOf("") }
    var parkingTariff by remember { mutableStateOf(0.0) }
    var randomPlaceId by remember { mutableStateOf<Int?>(null) }

    val prix = remember { mutableStateOf(0.0) }
    val context = LocalContext.current

    // Fetch parking tariff by ID
    LaunchedEffect(parkingId) {
        if (parkingId != null) {
            viewModelPark.getParkingTariffById(parkingId) { tariff ->
                parkingTariff = tariff ?: 0.0
            }
        }
    }

    // Function to fetch a random place ID
    fun fetchRandomPlaceId() {
        viewModelPlac.randomlyAvailablePlace { placeId ->
            randomPlaceId = placeId
        }
    }

    // Fetch randomly available place when the composable is first drawn
    LaunchedEffect(Unit) {
        fetchRandomPlaceId()
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
                if (date.isNotBlank() && heureEntr.isNotBlank() && heureSort.isNotBlank() && randomPlaceId != null) {
                    val prixValue = calculatePrice(heureEntr, heureSort, parkingTariff)
                    val reservation = Reservation(
                        id_reservation = 0, // or generate an appropriate ID
                        id_place = randomPlaceId!!, // Use randomPlaceId and ensure it's not null
                        date = date, // Sending date as string
                        heure_entree = heureEntr, // Sending time as string
                        heure_sortie = heureSort, // Sending time as string
                        code_qr = "ABCD",
                        prix = prixValue
                    )

                    viewModelReserv.createReservation(reservation) { isSuccess ->
                        if (isSuccess) {
                            // Reservation created successfully
                            Toast.makeText(context, "Reservation created successfully", Toast.LENGTH_SHORT).show()
                        } else {
                            // Failed to create reservation
                            Toast.makeText(context, "Failed to create reservation", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            },
            enabled = date.isNotBlank() && heureEntr.isNotBlank() && heureSort.isNotBlank() && randomPlaceId != null
        ) {
            Text(text = "Book Parking")
        }

        // Display the fetched random place ID
        if (randomPlaceId != null) {
            Text(text = "Random Place ID: $randomPlaceId", modifier = Modifier.padding(vertical = 16.dp))
        }

        // Button to fetch a new random place ID
        Button(
            onClick = { fetchRandomPlaceId() },
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text(text = "Fetch Random Place ID")
        }
    }
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
