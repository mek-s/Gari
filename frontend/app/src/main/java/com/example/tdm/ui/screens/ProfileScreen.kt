import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.tdm.MainActivity
import com.example.tdm.R
import com.example.tdm.ui.theme.orange


@Composable
fun DisplayMyProfile(navController: NavController, sharedPreferencesManager: SharedPreferencesManager,  viewModel: AuthViewModel) {
    var themeDark by remember { mutableStateOf(false) }
    var notificationEnabled by remember { mutableStateOf(false) }

    var isLoggedIn by remember { mutableStateOf<Boolean?>(null) }
    val username by remember { mutableStateOf(sharedPreferencesManager.getLocalUsername()) }


    LaunchedEffect(Unit) {
        isLoggedIn = sharedPreferencesManager.isLoggedIn()
    }

    if (isLoggedIn == false) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.noo),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp) // Adjust size as needed
                    .padding(bottom = 16.dp)
            )
            Text(
                text = "You are not authenticated",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Button(
                onClick = {
                    navController.navigate(Routes.Login.route)
                },
                colors = ButtonDefaults.buttonColors(Color(0xFFFF8264)),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Sign In")
            }
        }
    }
    else
    {
        val context = LocalContext.current
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                Header(navController, sharedPreferencesManager, viewModel)
            }
            Spacer(modifier = Modifier.height(22.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(10.dp)),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .shadow(1.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(22.dp)
                    ) {
                        MenuButton1("Personal Info ", Icons.Filled.Info) {
                            if (username != null) {
                                navController.navigate(Routes.InfoProfile.route)
                            } else {
                                Toast.makeText(context, "No username.", Toast.LENGTH_SHORT).show()
                            }
                        }
                        MenuButton1("Favorite Parkings", Icons.Filled.Favorite) {
                            // Handle My Interest click
                        }
                        MenuButton1("Security", Icons.Filled.Warning) {
                            if (username != null) {
                                navController.navigate(Routes.Security.route)
                            } else {
                                Toast.makeText(context, "No username.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }

                // Second Box: Contains toggle for notifications and theme
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .shadow(1.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                    ) {
                        SwitchWithIcon("Notifications", Icons.Filled.Notifications, notificationEnabled) {
                            notificationEnabled = it
                        }
                        SwitchWithIcon("Theme", if (themeDark) Icons.Filled.AddCircle else Icons.Filled.Add, themeDark) {
                            themeDark = it
                        }
                    }
                }

                // Third Box: Link to log out with an icon
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .shadow(2.dp)
                        .background(orange)
                ) {
                    MenuButton2("Log Out", Icons.Filled.ExitToApp) {
                        (context as MainActivity).signOut()
                        viewModel.setNavValue(0)
                        sharedPreferencesManager.setLoggedIn(false)
                        viewModel.user.value = null
                        navController.navigate(Routes.Home.route)
                    }
                }
            }
        }

    }


}
