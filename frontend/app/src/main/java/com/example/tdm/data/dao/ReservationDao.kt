import androidx.room.Dao
import androidx.room.Query


@Dao
interface ReservationDao {
    @Query("SELECT * FROM reservation")
     fun getAllReservations(): List<Reservation>

}