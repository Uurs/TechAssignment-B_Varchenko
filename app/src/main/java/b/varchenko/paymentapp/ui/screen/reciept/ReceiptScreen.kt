package b.varchenko.paymentapp.ui.screen.reciept

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import b.varchenko.domain.model.PurchaseReceipt
import b.varchenko.domain.model.PurchaseReceiptDetails
import b.varchenko.paymentapp.R
import b.varchenko.paymentapp.ui.component.Loading
import b.varchenko.paymentapp.ui.component.PrimaryTextButton
import java.util.Date

@Composable
fun ReceiptScreen(
    navigateBack: () -> Unit,
    viewModel: ReceiptViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        Loading()
        return
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            ReceiptScreenUI(state.details, navigateBack)
        }
    }
}

@Composable
fun ReceiptScreenUI(
    details: ReceiptViewState.Details?,
    navigateBack: () -> Unit,
) {
    if (details != null) {
        ReceiptDetails(
            isSuccess = details.isSuccess,
            transactionId = details.transactionId,
            purchaseAmount = details.purchaseAmount,
            currency = details.currency,
            overallPaidTax = details.overallPaidTax,
            overallPaidTip = details.overallPaidTip,
            overallDiscount = details.overallDiscount,
            finalAmount = details.finalAmount,
            navigateBack = navigateBack,
        )

    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(R.string.screen_receipt_message_no_receipt),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
            )

            PrimaryTextButton(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(),
                textRes = R.string.screen_receipt_no_content_button_ok,
                onClick = navigateBack,
            )
        }
    }
}

@Composable
private fun ReceiptDetails(
    isSuccess: Boolean,
    transactionId: String,
    purchaseAmount: Double,
    currency: String,
    overallPaidTax: Double,
    overallPaidTip: Double,
    overallDiscount: Double,
    finalAmount: Double,
    navigateBack: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        Icon(
            painterResource(R.drawable.ic_success),
            contentDescription = stringResource(
                R.string.screen_receipt_success_icon_content_description
            ),
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(128.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            stringResource(
                if (isSuccess) {
                    R.string.screen_receipt_label_status_success
                } else {
                    R.string.screen_receipt_label_status_failure
                }
            ),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            stringResource(
                R.string.screen_receipt_label_transaction_id,
                transactionId
            ),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            stringResource(
                R.string.screen_receipt_label_purchase_amount_with_currency,
                purchaseAmount,
                currency
            ),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            stringResource(
                R.string.screen_receipt_label_tax_and_tip,
                overallPaidTax,
                overallPaidTip,
                overallDiscount,
            ),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            stringResource(
                R.string.screen_receipt_label_final_amount,
                finalAmount,
            ),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(Modifier.weight(1f))
        PrimaryTextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = navigateBack,
            textRes = R.string.screen_receipt_button_ok,
        )
    }
}

@Preview
@Composable
fun ReceiptScreenPreviewWithContent() {
    val receipt = PurchaseReceiptDetails(
        receipt = PurchaseReceipt(
            transactionId = "1234567890",
            purchaseAmount = 100.0,
            currency = "USD",
            date = Date(),
            status = "success",
            discountAmount = 5.0,
            taxRate = 0.01,
            taxableAmount = 0.90,
            tipAmount = 5.0,
        ),
        overallPaidTax = 1.0,
        overallPaidTip = 5.0,
        overallDiscount = 5.0,
        finalAmount = 42.0,
    )
    ReceiptScreenUI(
        details = ReceiptViewState.Details(
            isSuccess = true,
            transactionId = receipt.receipt.transactionId,
            purchaseAmount = receipt.receipt.purchaseAmount,
            currency = receipt.receipt.currency,
            overallPaidTax = receipt.overallPaidTax,
            overallPaidTip = receipt.overallPaidTip,
            overallDiscount = receipt.overallDiscount,
            finalAmount = receipt.finalAmount,
        ),
        navigateBack = { },
    )
}

@Preview
@Composable
fun ReceiptScreenPreviewNoContent() {
    ReceiptScreenUI(
        details = null,
        navigateBack = { },
    )
}