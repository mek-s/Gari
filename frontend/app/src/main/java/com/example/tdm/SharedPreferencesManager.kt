import android.content.Context
import androidx.compose.runtime.mutableStateOf


class SharedPreferencesManager(val context: Context) {


    val Localuser = mutableStateOf<String?>(null)



    private val pref = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    fun isLoggedIn(): Boolean {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun setLocalUsername (user: String)
    {

        pref.edit().putString("Localuser", user).apply()

    }

    fun getLocalUsername(): String?
    {
        return pref.getString("Localuser", "user")
    }


    fun setLoggedIn(value: Boolean) {
        pref.edit().putBoolean(KEY_IS_LOGGED_IN, value).apply()
    }

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }
}
