import androidx.room.Dao
import androidx.room.Query
import com.example.tdm.data.models.Parking


@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>

}