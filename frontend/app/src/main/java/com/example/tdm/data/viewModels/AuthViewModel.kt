import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tdm.data.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import com.google.firebase.messaging.FirebaseMessaging
import java.io.File
import java.io.FileOutputStream

class AuthViewModel(
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val userRepository: UserRepository
) : ViewModel() {



    private val _currentUsername = MutableStateFlow("")
    val currentUsername = _currentUsername.asStateFlow()
    var user = mutableStateOf<String?>(null)
    var sso = mutableStateOf<Int?>(0)

    var nav by mutableStateOf<Int?>(0)
        private set

    private val _loginError = MutableStateFlow<String?>(null)
    val loginError = _loginError.asStateFlow()

    val username = sharedPreferencesManager.getLocalUsername()

    init {
        // Register for FCM token updates and send token to server when a new token is generated
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                if (!token.isNullOrEmpty()) {

                    username?.let { sendTokenToServer(it, token) }
                }
            } else {
                Log.e(TAG, "Fetching FCM registration token failed", task.exception)
            }
        }
    }

    private fun sendTokenToServer(username: String, token: String) {
        viewModelScope.launch {
            try {
                userRepository.sendTokenToServer(username, token)
                Log.d(TAG, "Token sent to server successfully")
            } catch (e: Exception) {
                Log.e(TAG, "Error sending token to server: ${e.message}")
            }
        }
    }

    companion object {
        private const val TAG = "AuthViewModel"
    }


    fun login(username: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = userRepository.login(username, password)

                    if (response.isSuccessful) {
                        nav = 1
                        val res = response.body()
                        Log.v("ParkingModel222", "Parkings received: ${response.body()}")
                        if (res != null) {
                            user.value = res
                            setLoggedIn(true)
                            sharedPreferencesManager.setLocalUsername(user.value!!)
                            Log.d("ParkingModel3333", "${user.value}")
                        }
                    } else {
                        Log.d("ParkingModel", "is null")

                    }
                } catch (e: Exception) {
                    Log.d("ParkingModel", e.message.toString())

                }
            }
        }
    }

    fun authenticateWithGoogle(idToken: String, callback: (Boolean) -> Unit) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser = task.result?.user
                    if (firebaseUser != null) {
                        viewModelScope.launch {
                            try {
                                val response = userRepository.getUserByEmail(firebaseUser.email!!)
                                if (response.isSuccessful && response.body() != null) {
                                    sso.value = 1
                                    // User exists, update shared preferences and state
                                    val user = response.body()!!
                                    _userr.value = user
                                    sharedPreferencesManager.setLocalUsername(user.username)
                                    sharedPreferencesManager.setLoggedIn(true)
                                    callback(true)
                                } else {
                                    sso.value = 1
                                    // User does not exist, create a new user
                                    val displayName = firebaseUser.displayName ?: ""
                                    val nameParts = displayName.split(" ")

                                    val prenom = if (nameParts.isNotEmpty()) nameParts[0] else ""
                                    val nom = if (nameParts.size > 1) nameParts[1] else ""

                                    val newUser = User(
                                        username = displayName,
                                        password = "", // Or generate a random password
                                        nom = nom,
                                        prenom = prenom,
                                        email = firebaseUser.email ?: "",
                                        photo = firebaseUser.photoUrl.toString()
                                    )
                                    val createResponse = userRepository.createUser(newUser)
                                    if (createResponse.isSuccessful) {
                                        sharedPreferencesManager.setLocalUsername(newUser.username)
                                        sharedPreferencesManager.setLoggedIn(true)
                                        callback(true)
                                    } else {
                                        callback(false)
                                    }
                                }
                            } catch (e: Exception) {
                                callback(false)
                            }
                        }
                    } else {
                        callback(false)
                    }
                } else {
                    callback(false)
                }
            }
    }

    fun updateUserInfo(user: User, onUpdateUserInfoResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            var success = false // Initialize success status
            try {
                val response = userRepository.updateUserInformation(user)
                if (response.isSuccessful) {
                    success = true // Set success to true upon successful user update
                } else {
                    // Handle unsuccessful response
                }
            } catch (e: Exception) {
                // Handle exceptions
            }
            // Invoke the callback with the success status
            onUpdateUserInfoResult(success)
        }
    }

    private val _changePasswordResult = MutableStateFlow<Boolean?>(null)
    val changePasswordResult: StateFlow<Boolean?> = _changePasswordResult
    fun updateUserPassword(username: String, newPassword: String) {
        viewModelScope.launch {
            var success = false // Initialize success status
            try {
                val response = userRepository.updateUserPassword(username, newPassword)
                if (response.isSuccessful) {
                    success = true // Set success to true upon successful password update
                } else {
                    // Handle unsuccessful response
                }
            } catch (e: Exception) {
                // Handle exceptions
            }
            _changePasswordResult.value = success // Update the result state
        }
    }

    fun resetChangePasswordResult() {
        _changePasswordResult.value = null // Reset the result state
    }

    fun setNavValue(newValue: Int?) {
        nav = newValue
    }


    private val _userr = MutableStateFlow<User?>(null)
    val userr: StateFlow<User?> = _userr

    fun getUserByUsername(username: String) {
        viewModelScope.launch {
            try {
                val response = userRepository.getUserByUsername(username)
                if (response.isSuccessful) {
                    val user = response.body()
                    _userr.value = user
                } else {
                    // Handle unsuccessful response
                }
            } catch (e: Exception) {
                // Handle exceptions
            }
        }
    }

    fun saveUserPhotoToStorage(bitmap: Bitmap, context: Context): String {
        val fileName = "user_photo_${System.currentTimeMillis()}.png"
        val file = File(context.filesDir, fileName)
        val outputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
        return fileName
    }

    fun uploadUserPhoto(filePath: String, onSuccess: (String) -> Unit, onError: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val file = File(filePath)
                val requestFile = file.asRequestBody("image/png".toMediaTypeOrNull())
                val body = MultipartBody.Part.createFormData("image", file.name, requestFile)

                val response = userRepository.uploadImage(body)
                if (response.isSuccessful) {
                    response.body()?.let { imageName ->
                        onSuccess(imageName)
                    } ?: onError(Exception("Image upload failed"))
                } else {
                    onError(Exception("Image upload failed: ${response.errorBody()?.string()}"))
                }
            } catch (e: Exception) {
                onError(e)
            }
        }
    }
    fun updateUserPhoto(username: String, photoName: String) {
        viewModelScope.launch {
            try {
                val response = userRepository.updateUserPhoto(username, photoName)
                if (response.isSuccessful) {

                } else {
                    // Handle unsuccessful response
                }
            } catch (e: Exception) {
                // Handle exceptions
            }
        }
    }


    fun createUser(user: User, onCreateUserResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            var success = false // Initialize success status
            try {
                val response = userRepository.createUser(user)
                if (response.isSuccessful) {
                    val createdUsername = response.body() ?: ""
                    if (createdUsername.isNotEmpty()) {
                        _currentUsername.value = createdUsername
                        sharedPreferencesManager.setLoggedIn(true)
                        success = true // Set success to true upon successful user creation
                    } else {
                        _loginError.value = "Failed to create user"
                    }
                } else {
                    _loginError.value = "Failed to create user: ${response.code()}"
                }
            } catch (e: Exception) {
                _loginError.value = "Failed to create user: ${e.message}"
            }
            // Invoke the callback with the success status
            onCreateUserResult(success)
        }
    }

    fun clearLoginError() {
        _loginError.value = null
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferencesManager.isLoggedIn()
    }

    fun setLoggedIn(value: Boolean) {
        sharedPreferencesManager.setLoggedIn(value)
    }



    class Factory(
        private val sharedPreferencesManager: SharedPreferencesManager,
        private val userRepository: UserRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AuthViewModel(sharedPreferencesManager, userRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
