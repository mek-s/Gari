import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tdm.data.models.Parking
import com.example.tdm.data.models.Reservation
import com.example.tdm.data.repositories.ReservationRespository
import com.example.tdm.ui.components.ReservationList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ReservationModel(private val reservationRespository: ReservationRespository) : ViewModel() {

    var allReservations = mutableStateOf<List<Reservation>>(emptyList())
    var allR = mutableStateOf(listOf<Reservation>())


    fun getAllReservations() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = reservationRespository.getAllReservations()
                if (response.isSuccessful) {
                    val reservations: List<Reservation>? = response.body()
                    // Update the mutable state with the list of reservations
                    allReservations.value = reservations ?: emptyList()
                } else {
                    // Handle unsuccessful response
                    println("Failed to fetch reservations: ${response.message()}")

                }
            }
        }
    }

    // Function to create a reservation
    fun createReservation(reservation: Reservation, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = reservationRespository.createReservation(reservation)
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful) {
                            callback(true)
                        } else {
                            callback(false)
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        callback(false)
                    }
                }
            }
        }
    }

    fun getReservationsByUsername(username: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                val response = reservationRespository.getReservationsByUsername(username)
                if (response.isSuccessful) {
                    allR.value = response.body() ?: emptyList()
                } else {
                    // Handle unsuccessful response
                    println("Failed to fetch reservations by username: ${response.message()}")
                    allR.value = emptyList()
                }
            }
        }

    }







    class Factory(private val reservationRespository: ReservationRespository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ReservationModel(reservationRespository) as T
        }
    }
}
