import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.training.Model.Message

@Composable
fun MessageCard(
    message: Message
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = if (message.isUser) {
            Alignment.End
        } else {
            Alignment.Start
        }
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = if (message.isUser) {

                    Color(52, 173, 86)
                } else {
                    Color(26, 84, 42)
                }
            )
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(8.dp),
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}
