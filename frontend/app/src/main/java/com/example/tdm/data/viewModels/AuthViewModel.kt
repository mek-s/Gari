import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.tdm.data.models.User
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _currentUsername = MutableStateFlow("")
    val currentUsername = _currentUsername.asStateFlow()

    private val _loginError = MutableStateFlow<String?>(null)
    val loginError = _loginError.asStateFlow()

    fun authenticate(username: String, password: String, onLoginResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            var success = false // Initialize success status
            // Your authentication logic remains the same
            try {
                val response = userRepository.login(username, password)

                if (response.isSuccessful) {
                    val loggedInUsername = response.body() ?: ""
                    if (loggedInUsername.isNotEmpty() && loggedInUsername != "Login failed") {
                        _currentUsername.value = loggedInUsername
                        sharedPreferencesManager.setLoggedIn(true)
                        success = true // Set success to true upon successful login
                    } else {
                        _loginError.value = "Authentication failed"
                    }
                } else {
                    _loginError.value = "Authentication failed: ${response.code()}"
                }
            } catch (e: Exception) {
                _loginError.value = "Authentication failed: ${e.message}"
            }
            // Invoke the callback with the success status
            onLoginResult(success)
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
