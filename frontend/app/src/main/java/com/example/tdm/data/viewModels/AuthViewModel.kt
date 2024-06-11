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
import java.io.File
import java.io.FileOutputStream

class AuthViewModel(
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _currentUsername = MutableStateFlow("")
    val currentUsername = _currentUsername.asStateFlow()
    var user = mutableStateOf<String?>(null)

    var nav by mutableStateOf<Int?>(0)
        private set

    private val _loginError = MutableStateFlow<String?>(null)
    val loginError = _loginError.asStateFlow()

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
                    val user = task.result?.user
                    if (user != null) {
                        this.user.value = user.displayName
                        sharedPreferencesManager.setLocalUsername(user.displayName ?: "")
                        sharedPreferencesManager.setLoggedIn(true)
                        callback(true)
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


    fun updateUserPhoto(username: String, photoName: String) {
        viewModelScope.launch {
            try {
                val response = userRepository.updateUserPhoto(username, photoName)
                if (response.isSuccessful) {
                    // Photo updated successfully
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
