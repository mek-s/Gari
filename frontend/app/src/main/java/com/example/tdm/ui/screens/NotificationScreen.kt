import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tdm.R


@Composable
fun NotificationScreen() {
    // Dummy list of notifications
    val notifications = listOf(
        Notification("Notification 1", "here is a notf 1"),
        Notification("Notification 2", "here is a notf 2"),
        Notification("Notification 3", "here is a notf 3"),
        Notification("Notification 4", "here is a notf 4"),
        Notification("Notification 5", "here is a notf 5"),
        Notification("Notification 6", "here is a notf 6"),
        Notification("Notification 7", "here is a notf 7"),
        Notification("Notification 8", "here is a notf 8"),
        Notification("Notification 9", "here is a notf 9")
    )
    var selectedNotification by remember { mutableStateOf<Notification?>(null) }


    LazyColumn {
        items(notifications) { notification ->
            NotificationItem(
                notification = notification,
                isSelected = notification == selectedNotification,
                onClick = { selectedNotification = notification }
            )
        }
    }
}

@Composable
fun NotificationItem(
    notification: Notification,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) Color(0xFFE3FAFC) else Color.White

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            // Image on the left side
            Image(
                painter = painterResource(id = R.drawable.notfr),
                contentDescription = "Notification Icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            // Text content
            Column {
                Text(
                    text = notification.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Text(
                    text = notification.description,
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
        }
    }
}
data class Notification(val name: String, val description: String)
