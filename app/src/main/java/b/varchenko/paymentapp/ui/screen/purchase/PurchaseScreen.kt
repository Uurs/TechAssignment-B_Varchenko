package b.varchenko.paymentapp.ui.screen.purchase

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import b.varchenko.paymentapp.R
import b.varchenko.paymentapp.ui.component.Loading
import b.varchenko.paymentapp.ui.component.PinPad
import kotlinx.coroutines.flow.collectLatest

private const val PURCHASE_AMOUNT_VALUE_FORMAT = "%.2f"

@Composable
fun PurchaseScreen(
    navigateToReceiptScreen: (String) -> Unit,
    viewModel: PurchaseViewModel = hiltViewModel(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(viewModel) {
        val showMessage: suspend (Int) -> Unit = {
            snackbarHostState.showSnackbar(
                context.getString(it),
                withDismissAction = true,
                duration = SnackbarDuration.Short
            )
        }
        viewModel.event.collectLatest {
            when (it) {
                PurchaseScreenEvent.NoInternetConnection ->
                    showMessage(R.string.screen_purchase_message_no_internet)

                PurchaseScreenEvent.InvalidAmount ->
                    showMessage(R.string.screen_purchase_message_invalid_amount)


                PurchaseScreenEvent.Error ->
                    showMessage(R.string.screen_purchase_message_generic_error)

                is PurchaseScreenEvent.OpenReceiptScreen ->
                    navigateToReceiptScreen(it.id)
            }
        }
    }

    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        Loading()
        return
    }

    val purchaseAmount = remember(state.purchaseAmount) {
        PURCHASE_AMOUNT_VALUE_FORMAT.format(state.purchaseAmount)
            .let { if (state.purchaseAmount < 10) "0$it" else it }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            PurchaseScreenUI(
                value = purchaseAmount,
                onValuePressed = { viewModel.onPinPadValuePress(it) },
                onConfirm = { viewModel.onConfirm() }
            )
        }
    }
}

@Composable
private fun PurchaseScreenUI(
    value: String,
    onValuePressed: (Int) -> Unit,
    onConfirm: () -> Unit,
) {

    Column {
        Column(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(48.dp))
            Text(
                stringResource(R.string.screen_purchase_title),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(Modifier.height(16.dp))
            Text(
                stringResource(R.string.screen_purchase_subtitle),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(Modifier.height(16.dp))
            Text(
                value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        PinPad(
            onValuePress = onValuePressed,
            onConfirmPress = onConfirm,
        )
    }
}

@Preview
@Composable
private fun PurchaseScreenPreview() {
    PurchaseScreenUI(
        value = "00.00",
        onValuePressed = {},
        onConfirm = {}
    )
}