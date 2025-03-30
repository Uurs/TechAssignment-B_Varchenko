package b.varchenko.domain.model

import java.util.Date

const val PURCHASE_RECEIPT_STATUS_SUCCESS = "Success"

data class PurchaseReceipt(
    val status: String,
    val transactionId: String,
    val currency: String,
    val discountAmount: Double,
    val purchaseAmount: Double,
    val taxRate: Double,
    val taxableAmount: Double,
    val tipAmount: Double,
    val date: Date,
) {
    val isSuccess: Boolean
        get() = status == PURCHASE_RECEIPT_STATUS_SUCCESS
}
