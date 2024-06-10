
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalContext
import com.example.tdm.MainActivity
import com.example.tdm.R

@Composable
fun DisplayLogin(viewModel: AuthViewModel, navHostController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isLoggedIn = remember { mutableStateOf(false) }
    var currentUsername by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    currentUsername = viewModel.user.value
    if (!currentUsername.isNullOrEmpty()) {
        LaunchedEffect(Unit) {
            navHostController.navigate(Routes.Home.route)
        }
    }

    if (isLoggedIn.value) {
        navHostController.navigate(Routes.Home.route)
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo"
            )
            Text(text = "Welcome Back ")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Login Into Your Account")

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "Username") },
                textStyle = TextStyle(color = Color.Black)
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.login(username, password)
                },
                enabled = username.isNotBlank() && password.isNotBlank()
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    navHostController.navigate(Routes.SignUp.route)
                }
            ) {
                Text(text = "Don't have an account? Sign up")
            }
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column {
                    Text(text = "Sign in with Google", style = MaterialTheme.typography.titleMedium)
                    Button(
                        onClick = {
                            (context as MainActivity).triggerSignIn()
                        },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text(text = "Sign In")
                    }
                }
            }
        }
    }
}
