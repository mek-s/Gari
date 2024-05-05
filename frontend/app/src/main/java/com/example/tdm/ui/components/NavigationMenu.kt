import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.tdm.data.models.Parking
import com.example.tdm.data.viewModels.ParkingModel
import com.example.tdm.ui.components.ParkingsList

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationMenu(
    navController: NavHostController,
   parkingModel: ParkingModel
) {
    val currentindex = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        bottomBar = {
            Surface(
                color = Color(0xFFE3FAFC),
            ) {
                BottomAppBar(
                    contentColor = MaterialTheme.colorScheme.primary,
                ) {
                    NavigationBar {
                        NavigationBarItem(
                            label = { Text(text = "Home") },
                            selected = currentindex == Routes.Home.route,
                            onClick = {
                                navController.navigate(Routes.Home.route)
                            },
                            icon = { Icon(Icons.Default.Home, contentDescription = "Home") }
                        )

                        NavigationBarItem(
                            label = { Text(text = "Map") },
                            selected = currentindex == Routes.Map.route,
                            onClick = { navController.navigate(Routes.Map.route) },
                            icon = { Icon(Icons.Default.Place, contentDescription = "Map") }
                        )

                        NavigationBarItem(
                            label = { Text(text = "MyBookings") },
                            selected = currentindex == Routes.MyResv.route,
                            onClick = { navController.navigate(Routes.MyResv.route) },
                            icon = { Icon(Icons.Default.DateRange, contentDescription = "MyBookings") }
                        )

                        NavigationBarItem(
                            label = { Text(text = "Profile") },
                            selected = currentindex == Routes.Profile.route,
                            onClick = { navController.navigate(Routes.Profile.route) },
                            icon = { Icon(Icons.Default.AccountBox, contentDescription = "Profile") }
                        )
                    }
                }
            }
        },
    ) {
        NavHost(navController = navController, startDestination = Routes.Home.route) {
            composable(Routes.Home.route) {
                ParkingsList(parkings = parkingModel.allRParkings.value, navController = navController)
            }
            composable(Routes.Map.route) { }
            composable(Routes.MyResv.route) { }
            composable(Routes.Profile.route) { }
        }
    }
}
