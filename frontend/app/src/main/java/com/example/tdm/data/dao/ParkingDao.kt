import androidx.room.Dao
import androidx.room.Query
import com.example.tdm.data.models.Parking


@Dao
interface ParkingDao {
    @Query("SELECT * FROM parking")
    suspend fun getAllParkings(): List<Parking>

}