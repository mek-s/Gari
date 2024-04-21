import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tdm.data.models.Parking


@Database(entities = [Parking::class], version = 1)
abstract class database : RoomDatabase() {
    abstract fun getParkingDao(): ParkingDao
    companion object {
        private var INSTANCE: database? = null
        fun getInstance(context: Context): database {
            var instance = INSTANCE
            synchronized(this) {
                if (instance == null) {
                    instance =
                        Room.databaseBuilder(
                            context, database::class.java,
                            "gari_database"
                        ).build()
                    INSTANCE = instance
                }
                return instance as database
            }
        }
    }
}

