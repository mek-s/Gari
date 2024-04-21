



import java.util.Date

data class Reservation(
    val idReservation: String,
    val idPlace: String, // Assuming FK stands for Foreign Key
    val date: Date,
    val heureEntree: String,
    val heureSortie: String,
    val codeQR: String,
    val prix: Double,
    val validite: Boolean
)
