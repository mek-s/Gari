import androidx.room.Dao
import androidx.room.Query

@Dao
interface PlaceDao {
    @Query("SELECT * FROM place")
    suspend fun getAllPlaces(): List<Place>

}