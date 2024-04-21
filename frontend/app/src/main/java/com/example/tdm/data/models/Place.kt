import androidx.room.Entity


@Entity(tableName = "place")
data class Place(
    val idPlace: String,
    val reservee: Boolean,
    val idParking: String
)