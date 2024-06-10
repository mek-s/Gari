import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext


import com.example.tdm.R
import com.example.tdm.URL


@Composable
fun DisplayLogin(viewModel: AuthViewModel, navHostController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isLoggedIn = remember { mutableStateOf(false) }
    var currentUsername by remember { mutableStateOf<String?>(null) }

    currentUsername = viewModel.user.value
    if (!currentUsername.isNullOrEmpty()) {
        LaunchedEffect(Unit ){
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
            Text(text = "Logged in as: ${viewModel.user.value}")
            // Display loggedInUsername if available

            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    navHostController.navigate(Routes.Home.route)
                },
            ) {
                Text(text = "Home Page")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    // Navigate to the sign-up screen
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
            Text(
                text = "Forgot your Password ? ",
                modifier = Modifier.clickable { /* Handle click */ }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Or Sign In With ")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val context = LocalContext.current
                Image(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "Facebook",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("$URL/oauth2/authorization/facebook"))
                            context.startActivity(intent)
                        }
                )

                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Google",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("$URL/oauth2/authorization/google"))
                            context.startActivity(intent)
                        }
                )

                Image(
                    painter = painterResource(id = R.drawable.twitter),
                    contentDescription = "Twitter",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("$URL/oauth2/authorization/twitter"))
                            context.startActivity(intent)
                        }
                )
            }

        }
    }
}
