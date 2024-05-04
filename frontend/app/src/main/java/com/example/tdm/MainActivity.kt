package com.example.tdm

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
<<<<<<< HEAD
import androidx.compose.ui.tooling.preview.Preview
import com.example.tdm.ui.theme.TDMTheme

class MainActivity : androidx.activity.ComponentActivity() {
=======
import com.example.tdm.data.dataModels.ReservationModel
import com.example.tdm.ui.theme.TDMTheme

class MainActivity : androidx.activity.ComponentActivity() {

    private val reservationModel: ReservationModel by viewModels {
        ReservationModel.Factory((application as TDMApplication).reservationRespository)
    }
>>>>>>> 53cad78bb88fc397977786110d74d193b109e58b
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TDMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
<<<<<<< HEAD

=======
                  GetMain(reservationModel)
>>>>>>> 53cad78bb88fc397977786110d74d193b109e58b
                }
            }
        }
    }
}

