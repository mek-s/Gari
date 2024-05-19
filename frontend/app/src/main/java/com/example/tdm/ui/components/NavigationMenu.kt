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
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.tdm.data.viewModels.ParkingModel
import com.example.tdm.data.viewModels.PlaceModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationMenu(
    navController: NavHostController,
    parkingModel: ParkingModel,
    authViewModel: AuthViewModel,
    placeModel: PlaceModel,
    reservationModel: ReservationModel
) {
    val context = LocalContext.current
    val currentindex =navController.currentBackStackEntryAsState().value?.destination?.route

    val sh = SharedPreferencesManager(context)
    val isLoggedIn = sh.isLoggedIn()
    val LocalUsername = sh.getLocalUsername()

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
                            selected = currentindex == Routes.MyReserv.route,
                            onClick = { navController.navigate(Routes.MyReserv.route) },
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
            composable(Routes.Home.route) {  DisplayHome(navController ,parkingModel) }
            composable(Routes.Map.route) {  }

            composable(Routes.Profile.route) {
                if (LocalUsername != null) {
                    DisplayMyProfile(
                        isLoggedIn = isLoggedIn,
                        LocalUsername = LocalUsername,
                        viewModel = authViewModel,
                        navHostController = navController
                    )
                }
            }
            composable(Routes.Reserv.route)
            {
                val parkingId = it.arguments?.getString("parkingId")?.toInt()
                if (LocalUsername != null) {
                    DisplayReservation(parkingId,isLoggedIn, LocalUsername, reservationModel, parkingModel, placeModel, navController )
                }
            }



            composable(Routes.ParkingDetails.route) {
                val parkingId = it.arguments?.getString("parkingId")?.toInt()
                DisplayParkingDetails(navController,parkingModel , parkingId)
            }
            composable(Routes.Login.route) {
                DisplayLogin(authViewModel, navController)
            }


            composable(Routes.MyReserv.route) {

                if (LocalUsername != null) {
                    DisplayMyReservations(isLoggedIn = isLoggedIn, username  = LocalUsername,  viewModel = authViewModel, navHostController = navController, viewModelReserv = reservationModel )
                }
            }






            composable(Routes.SignUp.route)
            {
               DisplaySignUp(authViewModel, navController)
            }
        }
    }
}
