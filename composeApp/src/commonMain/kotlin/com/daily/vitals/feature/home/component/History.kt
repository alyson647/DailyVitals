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
import com.daily.vitals.domain.entry.model.Entry
import com.daily.vitals.feature.home.component.HistoryEntry
import dailyvitals.composeapp.generated.resources.Res
import dailyvitals.composeapp.generated.resources.last_five_days
import org.jetbrains.compose.resources.stringResource

@Composable
fun History(
    entries: List<Entry>
) {
    val filteredEntries = entries.sortedByDescending { it.id }.take(5)
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
        filteredEntries.forEach { entry ->
            HistoryEntry(
                date = entry.id,
                fasting = entry.fasting,
                postMeal = entry.postMeal)
        }
    }
}
