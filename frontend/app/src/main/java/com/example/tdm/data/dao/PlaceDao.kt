import androidx.room.Dao
import androidx.room.Query

@Dao
interface PlaceDao {
    @Query("SELECT * FROM place")
     fun getAllPlaces(): List<Place>

}