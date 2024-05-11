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
import com.example.tdm.data.viewModels.ParkingModel
import com.example.tdm.ui.theme.TDMTheme

class MainActivity : androidx.activity.ComponentActivity() {
    private val parkingModel: ParkingModel by viewModels {
        ParkingModel.Factory((application as TDMApplication).parkingRepository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TDMTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationMenu(navController = rememberNavController()  , parkingModel)
                }
            }
        }
    }
}
