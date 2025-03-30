package b.varchenko.paymentapp.ui.screen.reciept

data class ReceiptViewState(
    val isLoading: Boolean = false,
    val details: Details? = null,
) {
    data class Details(
        val isSuccess: Boolean,
        val transactionId: String,
        val purchaseAmount: Double,
        val currency: String,
        val overallPaidTax: Double,
        val overallPaidTip: Double,
        val overallDiscount: Double,
        val finalAmount: Double,
    )
}
