import androidx.room.Entity


@Entity(tableName = "user")
data class User(
    val  username: String,
    val  password: Boolean,
    val idParking: String
)