package b.varchenko.paymentapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import b.varchenko.paymentapp.R

@Composable
fun PinPadConfirmButton(
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onConfirm,
        modifier = modifier
            .padding(2.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.small
            )

    ) {
        Text(
            stringResource(R.string.pin_pad_confirm_button),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Preview
@Composable
fun PinPadConfirmButtonPreview() {
    PinPadConfirmButton(
        onConfirm = {}
    )
}