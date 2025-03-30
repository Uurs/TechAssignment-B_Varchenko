package b.varchenko.data.dto

import java.util.Date

data class PurchaseReceiptDto(
    val amount: Amount,
    val status: String,
    val transactionDetails: TransactionDetails,
    val transactionId: String
) {
    data class Amount(
        val currency: String,
        val discountAmount: Double,
        val purchaseAmount: Double,
        val taxRate: Double,
        val taxableAmount: Double,
        val tipAmount: Double
    )

    data class TransactionDetails(
       val timestamp: Date
    )
}
