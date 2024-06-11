import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tdm.data.dao.ReservationDao
import com.example.tdm.data.models.Reservation
import com.example.tdm.data.repositories.ReservationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ReservationModel(
    private val reservationRepository: ReservationRepository,
    private val reservationDao: ReservationDao
) : ViewModel() {

    var allReservations = mutableStateOf<List<Reservation>>(emptyList())

    var created by mutableStateOf<Int?>(0)
        private set


    // Function to create a reservation
    fun createReservation(
        idParking: Int,
        idPlace: Int,
        date: String,
        heureEntree: String,
        heureSortie: String,
        prix: Double,
        codeQr: String,
        username: String
    ) {
        viewModelScope.launch {
            try {
                val reservation = Reservation(
                    id_reservation = 0,
                    id_place = idPlace,
                    idParking = idParking,
                    date = date,
                    heure_entree = heureEntree,
                    heure_sortie = heureSortie,
                    codeQr = codeQr,
                    prix = prix,
                    username = username
                )
                val response = reservationRepository.createReservation(reservation)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val createdReservation = response.body()
                        if (createdReservation != null) {
                            reservationDao.insertReservation(createdReservation)
                            created = 1
                            println("Reservation created successfully")
                        } else {
                            println("Failed to create reservation (createdReservation is null)")

                        }
                    } else {
                        println("Failed to create reservation (response not successful)")

                    }
                }
            } catch (e: Exception) {
                println("Exception occurred: ${e.message}")
                withContext(Dispatchers.Main) {

                }
            }
        }
    }





    var allReservationss = mutableStateOf<List<Reservation>>(emptyList())
    var allR = mutableStateOf(listOf<Reservation>())

    fun getReservationsByUsername(username: String) {
        viewModelScope.launch {
            try {
                val response = reservationRepository.getReservationsByUsername1(username)
                if (response.isSuccessful) {
                    val reservationsFromServer = response.body() ?: emptyList()
                    allReservationss.value = reservationsFromServer

                    // Insert each reservation into the local database on the IO dispatcher
                    withContext(Dispatchers.IO) {
                        reservationsFromServer.forEach { reservation ->
                            reservationDao.insertReservation(reservation)
                        }
                    }
                } else {
                    // Handle unsuccessful response
                    Log.e("ReservationModelll", "Failed to fetch reservations by username: ${response.message()}")

                    // Fetch reservations from the local database on the IO dispatcher
                    val reservationsFromLocal = withContext(Dispatchers.IO) {
                        reservationDao.getReservationsByUsername(username)
                    }
                    allReservationss.value = reservationsFromLocal
                }
            } catch (e: Exception) {
                // Handle exceptions
                Log.e("ReservationModelll", "Exception occurred: ${e.message}")

                // Fetch reservations from the local database on the IO dispatcher
                val reservationsFromLocal = withContext(Dispatchers.IO) {
                    reservationDao.getReservationsByUsername(username)
                }
                allReservationss.value = reservationsFromLocal
            }
        }
    }



    private val _reservation = MutableLiveData<Reservation?>()
    val reservation: LiveData<Reservation?> get() = _reservation

    // Method to get a reservation by ID
    fun getReservationById(id: Int) {
        viewModelScope.launch {
            try {
                val reservation = withContext(Dispatchers.IO) {
                    reservationRepository.getReservationById(id)
                }
                if (reservation != null) {
                    withContext(Dispatchers.IO) {
                        reservationDao.insertReservation(reservation)
                    }
                    _reservation.postValue(reservation)
                } else {
                    val localReservation = withContext(Dispatchers.IO) {
                        reservationDao.getReservationById(id)
                    }
                    _reservation.postValue(localReservation)
                }
            } catch (e: Exception) {
                Log.e("ReservationModel", "Exception occurred: ${e.message}")

            }
        }
    }

    class Factory(private val reservationRespository: ReservationRepository, private val reservationDao: ReservationDao) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ReservationModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ReservationModel(reservationRespository, reservationDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }




}





