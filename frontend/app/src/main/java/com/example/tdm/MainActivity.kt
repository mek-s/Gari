package com.example.tdm

import AuthViewModel
import NavigationMenu
import ReservationModel
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.tdm.data.viewModels.ParkingModel
import com.example.tdm.data.viewModels.PlaceModel
import com.example.tdm.ui.theme.TDMTheme
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : androidx.activity.ComponentActivity() {
    private val parkingModel: ParkingModel by viewModels {
        ParkingModel.Factory((application as TDMApplication).parkingRepository)
    }

    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModel.Factory(
            (application as TDMApplication).sharedPreferencesManager,
            (application as TDMApplication).userRespository
        )
    }

    private val placeModel: PlaceModel by viewModels {
        PlaceModel.Factory((application as TDMApplication).placeRepository)
    }


    private val reservationModel: ReservationModel by viewModels {
        ReservationModel.Factory(
            (application as TDMApplication).reservationRespository,
            (application as TDMApplication).reservationDao
        )

    }

    private lateinit var auth: FirebaseAuth

    private val signInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val data = result.data
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)
            if (account != null) {
                authViewModel.authenticateWithGoogle(account.idToken!!) { success ->
                    if (success) {
                        Toast.makeText(this, "Signed in successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } catch (e: ApiException) {
            Toast.makeText(this, "Google sign in failed: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TDMTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationMenu(navController = rememberNavController(), parkingModel, authViewModel, placeModel, reservationModel)
                }
            }
        }
    }

    private fun signOutAndSignIn() {
        val googleSignInClient = GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN)
        googleSignInClient.signOut().addOnCompleteListener {
            signIn()
        }
    }

    fun signOut(){
        val googleSignInClient = GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN)
        googleSignInClient.signOut()
    }

    fun signIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = googleSignInClient.signInIntent
        signInLauncher.launch(signInIntent)
    }

    // Use this method in DisplayLogin composable instead of signIn
    fun triggerSignIn() {
        signOutAndSignIn()
    }

}