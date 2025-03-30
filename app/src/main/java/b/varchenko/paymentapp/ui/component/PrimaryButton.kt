package b.varchenko.paymentapp.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import b.varchenko.paymentapp.R

@Composable
fun PrimaryTextButton(
    modifier: Modifier,
    textRes: Int,
    onClick: () -> Unit,

) {
    Button(
        onClick,
        modifier = modifier
            .padding(16.dp)
            .height(48.dp),
    ) {
        Text(
            stringResource(textRes),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }

}