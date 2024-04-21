import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationMenu(
    navController: NavHostController,
    currentRoute: String?,
    onItemClick: (String) -> Unit
) {
    val currentindex =navController.currentBackStackEntryAsState().value?.destination?.route
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                NavigationBar {
                    NavigationBarItem(
                        label = { Text(text = "Home") },
                        selected = currentRoute == Routes.Home.route,
                        onClick = { onItemClick(Routes.Home.route) },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") }
                    )
                    NavigationBarItem(
                        label = { Text(text = "MyResv") },
                        selected = currentRoute == Routes.MyResv.route,
                        onClick = { onItemClick(Routes.MyResv.route) },
                        icon = { Icon(Icons.Default.DateRange, contentDescription = "MyResv") }
                    )
                    NavigationBarItem(
                        label = { Text(text = "Favorite") },
                        selected = currentRoute == Routes.Favorite.route,
                        onClick = { onItemClick(Routes.Favorite.route) },
                        icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorite") }
                    )
                    NavigationBarItem(
                        label = { Text(text = "Map") },
                        selected = currentRoute == Routes.Map.route,
                        onClick = { onItemClick(Routes.Map.route) },
                        icon = { Icon(Icons.Default.LocationOn, contentDescription = "Map") }
                    )
                    NavigationBarItem(
                        label = { Text(text = "Profile") },
                        selected = currentRoute == Routes.Profile.route,
                        onClick = { onItemClick(Routes.Profile.route) },
                        icon = { Icon(Icons.Default.AccountBox, contentDescription = "Profile") }
                    )
                }
            }
        }
    ) {
        NavHost(navController = navController, startDestination = Routes.Home.route) {
            // Define your destinations/composables here
        }
    }
}