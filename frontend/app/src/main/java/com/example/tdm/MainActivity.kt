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
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.navigation.compose.rememberNavController
import com.example.tdm.data.viewModels.ParkingModel
import com.example.tdm.data.viewModels.PlaceModel
import com.example.tdm.ui.theme.TDMTheme

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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TDMTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationMenu(navController = rememberNavController()  , parkingModel, authViewModel, placeModel, reservationModel )
                }
            }
        }
    }
}
