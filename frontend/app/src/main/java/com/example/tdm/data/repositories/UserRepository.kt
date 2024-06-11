
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

    suspend fun updateUserInformation(user: User): Response<String> {
        return endpoint.updateUserInformation(user)
    }


    suspend fun updateUserPassword(username: String, newPassword: String): Response<String> {
        return endpoint.updateUserPassword(username, newPassword)
    }

    suspend fun getUserByUsername(username: String): Response<User> {
        return endpoint.getUserByUsername(username)
    }

    suspend fun updateUserPhoto(username: String, photoName: String): Response<String> {
        return endpoint.updateUserPhoto(username, photoName)
    }

    suspend fun sendTokenToServer(username: String, token: String): Response<Unit> {
        return endpoint.sendTokenToServer(TokenRequest(username, token))
    }


}
