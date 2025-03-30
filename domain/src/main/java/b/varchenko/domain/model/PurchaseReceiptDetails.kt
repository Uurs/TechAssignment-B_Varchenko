package b.varchenko.domain.model

data class PurchaseReceiptDetails(
    val receipt: PurchaseReceipt,
    val overallPaidTax: Double,
    val overallPaidTip: Double,
    val overallDiscount: Double,
    val finalAmount: Double,
)
