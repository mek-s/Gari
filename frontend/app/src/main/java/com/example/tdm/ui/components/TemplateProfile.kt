import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.tdm.R
import com.example.tdm.URL
import com.example.tdm.ui.theme.darkBlue

@Composable
fun Header(
    navController: NavController,
    sharedPreferencesManager: SharedPreferencesManager,
    authViewModel: AuthViewModel
) {
    val username by remember { mutableStateOf(sharedPreferencesManager.getLocalUsername()) }
    val user by authViewModel.userr.collectAsState()

    LaunchedEffect(username) {
        username?.let { authViewModel.getUserByUsername(it) }
    }

    var userPhoto by remember { mutableStateOf(user?.photo) }
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        bitmap?.let {
            val filePath = authViewModel.saveUserPhotoToStorage(it, context)
            authViewModel.updateUserPhoto(username!!, filePath)
            userPhoto = filePath
        }
    }

    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            val filePath = authViewModel.saveUserPhotoToStorage(bitmap, context)
            authViewModel.updateUserPhoto(username!!, filePath)
            userPhoto = filePath
        }
    }

    Spacer(modifier = Modifier.width(10.dp))
    Box(modifier = Modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.returnn),
                contentDescription = "Return",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.navigate(Routes.Profile.route) }
            )

            Image(
                painter = painterResource(id = R.drawable.notiff),
                contentDescription = "Notification",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.navigate(Routes.Notification.route) }
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 20.dp)
            ) {


            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(50))
                    .background(color = darkBlue),
                contentAlignment = Alignment.Center
            ) {
                if (!userPhoto.isNullOrEmpty()) {
                    AsyncImage(
                        model = URL+userPhoto,
                        contentDescription = "User Photo",
                        modifier = Modifier
                            .size(20.dp)
                            .clip(RoundedCornerShape(50))
                    )
                } else {
                    Text(
                        text = username?.firstOrNull()?.toString() ?: "U",
                        color = Color.White,
                        fontSize = 24.sp
                    )
                }

            }
                Text(
                    text = username ?: "",
                    color = Color.Black,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier

                        .padding(top = 8.dp)
                )

            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Button(onClick = { launcher.launch() }) {
                    Text("Take Picture")
                }

                Button(onClick = { galleryLauncher.launch("image/*") }) {
                    Text("Choose from Gallery")
                }
            }
        }
    }
}
