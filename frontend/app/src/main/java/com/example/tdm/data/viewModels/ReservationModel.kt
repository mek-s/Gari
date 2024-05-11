import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tdm.data.models.Reservation
import com.example.tdm.data.repositories.ReservationRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ReservationModel(private val reservationRespository: ReservationRespository) : ViewModel() {

    var allReservations = mutableStateOf<List<Reservation>>(emptyList())


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

    class Factory(private val reservationRespository: ReservationRespository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ReservationModel(reservationRespository) as T
        }
    }
}
