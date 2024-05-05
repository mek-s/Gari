import android.provider.ContactsContract.CommonDataKinds.Website.URL
import com.example.tdm.data.models.Parking
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface parkingEndpoint {
    @GET("parkings/all")
    suspend fun getAllParkings(): Response<List<Parking>>


    companion object {
        var endpoint: parkingEndpoint? = null
        fun createEndpoint(): parkingEndpoint {
            if (endpoint == null) {
                endpoint = Retrofit.Builder().baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create()).build()
                    .create(parkingEndpoint::class.java)
            }

            return endpoint!!
        }
    }
}
