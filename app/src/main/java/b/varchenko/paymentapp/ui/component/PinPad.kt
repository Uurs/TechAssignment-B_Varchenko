package b.varchenko.paymentapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PinPad(
    onValuePress: (Int) -> Unit,
    onConfirmPress: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.surfaceContainerLow)
    ) {
        (1 .. 9).chunked(3).forEach { row ->
            Row {
                row.forEach { number ->
                    PinPadButton(
                        number = number,
                        onValuePress = onValuePress,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
        Row {
            Spacer(modifier = Modifier.weight(1f))
            PinPadButton(
                number = 0,
                onValuePress = onValuePress,
                modifier = Modifier.weight(1f)
            )
            PinPadConfirmButton(
                onConfirm = onConfirmPress,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview
@Composable
fun PinPadPreview() {
    PinPad(
        onValuePress = {},
        onConfirmPress = {}
    )
}