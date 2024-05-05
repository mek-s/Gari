import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tdm.data.viewModels.ParkingModel


@Composable
fun GetParking(parkingModel: ParkingModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DisplayLoading(loading = parkingModel.loading.value)
        LazyColumn(Modifier.height(1000.dp)) {
            items(parkingModel.allRParkings.value) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .padding(4.dp)
                        .background(Color(0xFFE0E0E0))
                        .clickable {}


                ) {

                    AsyncImage(
                        model = URL + it.image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(8.dp)
                    )

                    Column(
                        modifier = Modifier.weight(2f)
                    ) {
                        Text(
                            text = it.name, fontWeight = FontWeight.Bold,
                            fontSize = 12.sp, color = Color(0xFFB125EA)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = it.description,
                            fontSize = 11.sp, color = Color(0xFFB125BA)
                        )
                    }
                }
            }
        }
        DisplayErrorMessage(dogModel.displayMsg.value)
    }

    // err : boucle infinie
    // dogModel.getAllDogs()

}

@Composable
fun DisplayLoading(loading: Boolean) {
    if (loading == true) {
        CircularProgressIndicator()
    }

}

@Composable
fun DisplayErrorMessage(display: Boolean) {
    val context = LocalContext.current
    if (display) {
        Toast.makeText(context, "An arror happened", Toast.LENGTH_SHORT).show()
    }
}