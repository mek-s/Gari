import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun InfoProfileScreen(
    sharedPreferencesManager: SharedPreferencesManager,
    userModel: AuthViewModel,
    navController: NavHostController
) {
    val context = LocalContext.current
    val username = remember { sharedPreferencesManager.getLocalUsername() }
    val user by userModel.userr.collectAsState()

    // Fetch user info when the composable is composed
    LaunchedEffect(username) {
        if (username != null) {
            userModel.getUserByUsername(username)
        } else {
            Toast.makeText(context, "Username is missing. Please login again.", Toast.LENGTH_SHORT).show()
        }
    }

    var nom by remember { mutableStateOf(user?.nom ?: "") }
    var prenom by remember { mutableStateOf(user?.prenom ?: "") }
    var email by remember { mutableStateOf(user?.email ?: "") }


    // Update fields when user changes
    LaunchedEffect(user) {
        user?.let {
            nom = it.nom ?: ""
            prenom = it.prenom ?: ""
            email = it.email ?: ""

        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Header(navController, sharedPreferencesManager, userModel)
        }

        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        ) {
            // Username field
            OutlinedTextField(
                value = username ?: "",
                onValueChange = { /* No need to change username here */ },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                enabled = false
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Name field
            OutlinedTextField(
                value = nom,
                onValueChange = { nom = it },
                label = { Text("Last Name") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Surname field
            OutlinedTextField(
                value = prenom,
                onValueChange = { prenom = it },
                label = { Text("First Name") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    user?.let {
                        it.nom = nom
                        it.prenom = prenom
                        userModel.updateUserInfo(it) { success ->
                            if (success) {
                                Toast.makeText(context, "User info updated successfully", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Failed to update user info", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(Color(0xFFFF8264)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Text(text = "Save Info")
            }
        }
    }
}


