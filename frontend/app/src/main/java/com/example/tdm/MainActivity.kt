package com.example.tdm

import NavigationMenu
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.tdm.data.models.ReservationModel
import com.example.tdm.ui.theme.TDMTheme

class MainActivity : androidx.activity.ComponentActivity() {

    private val reservationModel: ReservationModel by viewModels {
        ReservationModel.Factory((application as TDMApplication).reservationRespository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TDMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationMenu(navController = rememberNavController() )
                }
            }
        }
    }
}
