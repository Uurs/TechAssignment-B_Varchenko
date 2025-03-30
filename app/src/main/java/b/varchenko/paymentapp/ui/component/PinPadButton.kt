package b.varchenko.paymentapp.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PinPadButton(
    number: Int,
    onValuePress: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextButton(
        onClick = { onValuePress(number) },
        modifier = modifier
            .padding(2.dp),
    ) {
        Text(
            text = number.toString(),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.headlineLarge,
        )
    }
}

@Preview
@Composable
fun PinPadButtonPreview() {
    PinPadButton(
        number = 1,
        onValuePress = {}
    )
}