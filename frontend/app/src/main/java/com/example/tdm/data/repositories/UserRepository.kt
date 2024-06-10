
import com.example.tdm.data.models.User
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response

class UserRepository(private val endpoint: Endpoint) {

    companion object {
        private const val TAG = "UserRepository"
    }



    suspend fun login(username: String, password: String): Response<String> {
        val formData = "username=$username&password=$password"
        val requestBody = formData.toRequestBody("application/x-www-form-urlencoded".toMediaTypeOrNull())

        return endpoint.login(requestBody)
    }



    suspend fun createUser(user: User): Response<String> {
        return endpoint.createUser(user)
    }
}
