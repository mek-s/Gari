import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun DisplayMyProfile(
    isLoggedIn: Boolean,
    LocalUsername : String,
    viewModel: AuthViewModel,
    navHostController: NavHostController
) {

    if ( isLoggedIn) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(

                onClick = {
                    viewModel.setLoggedIn(false)
                    navHostController.navigate(Routes.Home.route)
                }
            ) {
                Text(text = "Logout")
            }
            Text(text = "Welcome, $LocalUsername")
        }
        //val currentUsername = viewModel.user.value

    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = "Please log in to view reservations.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Button(
                onClick = { navHostController.navigate(Routes.Login.route) },

                colors = ButtonDefaults.buttonColors(Color(0xFFFF5722)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Text("Log In")
            }

        }



    }
}

