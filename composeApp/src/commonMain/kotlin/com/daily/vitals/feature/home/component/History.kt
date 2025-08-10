import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.daily.vitals.feature.home.component.HistoryEntry
import dailyvitals.composeapp.generated.resources.Res
import dailyvitals.composeapp.generated.resources.last_five_days
import org.jetbrains.compose.resources.stringResource
import kotlin.random.Random

@Composable
fun History() {
    val random = Random.Default
    val dateLabels = listOf(
        "July 6, 2025",
        "July 7, 2025",
        "July 8, 2025",
        "July 9, 2025",
        "July 10, 2025"
    )

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(Res.string.last_five_days),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelLarge,
        )
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outline
        )

        for (date in dateLabels) {
            val value1 = random.nextInt(80, 201)
            val value2 = random.nextInt(80, 201)
            HistoryEntry(date, value1, value2)
        }
    }
}
