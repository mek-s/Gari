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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun SecurityScreen(userModel: AuthViewModel, navController: NavController, sharedPreferencesManager: SharedPreferencesManager) {
    var passActual by remember { mutableStateOf("") }
    var newPass by remember { mutableStateOf("") }
    var newPassConfirm by remember { mutableStateOf("") }
    val context = LocalContext.current
    val username = remember { sharedPreferencesManager.getLocalUsername() }

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
            // Actual Password field
            OutlinedTextField(
                value = passActual,
                onValueChange = { passActual = it },
                label = { Text("Actual Password") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // New Password field
            OutlinedTextField(
                value = newPass,
                onValueChange = { newPass = it },
                label = { Text("New Password") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Confirm New Password field
            OutlinedTextField(
                value = newPassConfirm,
                onValueChange = { newPassConfirm = it },
                label = { Text("Confirm New Password") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp)) // Add space between text fields and button

            // Button to save information
            Button(
                onClick = {
                    if (newPass == newPassConfirm) {
                        username?.let {
                            userModel.updateUserPassword(it, newPass)
                        }
                    } else {
                        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                    }
                },
                enabled = passActual.isNotBlank() && newPass.isNotBlank() && newPassConfirm.isNotBlank(),
                colors = ButtonDefaults.buttonColors(Color(0xFF00C8CB)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Text(text = "Save New Password")
            }
        }
    }

    // Observe changePasswordResult
    val changePasswordResult by userModel.changePasswordResult.collectAsState()
    LaunchedEffect(changePasswordResult) {
        changePasswordResult?.let { result ->
            if (result) {
                Toast.makeText(context, "Password changed successfully", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
                userModel.resetChangePasswordResult()
            } else {
                Toast.makeText(context, "Error: Can't change password", Toast.LENGTH_SHORT).show()
                userModel.resetChangePasswordResult()
            }
        }
    }
}
