import androidx.room.Dao
import androidx.room.Query
import com.example.tdm.data.models.Parking


@Dao
interface ReservationDao {
    @Query("SELECT * FROM reservation")
    suspend fun getAllResv(): List<Reservation>

}