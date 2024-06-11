import com.example.tdm.URL
import com.example.tdm.data.models.Parking
import com.example.tdm.data.models.Place
import com.example.tdm.data.models.Reservation
import com.example.tdm.data.models.User
import com.google.gson.GsonBuilder
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface Endpoint {
    @GET("parkings/all")
    suspend fun getAllParkings(): Response<List<Parking>>

    @GET("parkings/{id}")
    suspend fun getParkingById(@Path("id") id: Int) : Response<Parking>

    @GET("places/all")
    suspend fun getAllPlaces(): Response<List<Place>>

    @GET("reservations/all")
    suspend fun getAllResesrvations(): Response<List<Reservation>>

    @GET("reservation/byUsername/{username}")
    suspend fun getReservationsByUsername(@Path("username") username: String): Response<List<Reservation>>



    @POST("user/login")
    suspend fun login(@Body requestBody: RequestBody): Response<String>

    @POST("user/register")
    suspend fun createUser(@Body user: User): Response<String>

    @GET("user/getUserByEmail/{email}")
    suspend fun getUserByEmail(@Path("email") email: String): Response<User>
    @PUT("user/update-info/{username}")
    suspend fun updateUserInformation(@Body user: User): Response<String>

    @GET("user/{username}")
    suspend fun getUserByUsername(@Path("username") username: String): Response<User>

    @PUT("user/updatePhoto/{username}")
    suspend fun updateUserPhoto(
        @Query("username") username: String,
        @Query("photoName") photoName: String
    ): Response<String>

    @PUT("user/update-password/{username}")
    suspend fun updateUserPassword(@Query("username") username: String, @Query("newPassword") newPassword: String): Response<String>

    @GET("places/unreserved/random")
    suspend fun getRandomUnreservedPlaceId(): Response<Int?>


    @GET("places/unreserved/random/{parkingId}")
    suspend fun getRandomUnreservedPlaceId(@Path("parkingId") parkingId: Int): Response<Int?>


    @GET("parkings/tarif/{id}")
    suspend fun getParkingTariffById(@Path("id") id: Int): Response<Double>

    @POST("reservation/create")
    suspend fun createReservation(@Body reservation: Reservation): Response<Reservation>



    @POST("places/reserve/{id}")
    suspend fun reservePlace(@Path("id") id: Int): Response<Unit>


    @GET("reservation/{id}")
    suspend fun getReservationById(@Path("id") id: Int): Response<Reservation>





    companion object {
        var endpoint: Endpoint? = null
        var gson = GsonBuilder()
            .setLenient()
            .create()

        fun createEndpoint(): Endpoint {
            if (endpoint == null) {
                endpoint = Retrofit.Builder().baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build()
                    .create(Endpoint::class.java)
            }

            return endpoint!!
        }
    }
}
