import android.content.Context


class SharedPreferencesManager(val context: Context) {

    private val pref = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    fun isLoggedIn(): Boolean {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun setLoggedIn(value: Boolean) {
        pref.edit().putBoolean(KEY_IS_LOGGED_IN, value).apply()
    }

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }
}
