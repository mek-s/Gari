import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController



@Composable
fun DisplayMyReservations (
    isLoggedIn: Boolean,
    viewModel: AuthViewModel,
    navHostController: NavHostController)
{
    if (isLoggedIn) {
        Button(onClick = {

        }) {
            Text(text = "Hello My Reservations")

        }
    } else {
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