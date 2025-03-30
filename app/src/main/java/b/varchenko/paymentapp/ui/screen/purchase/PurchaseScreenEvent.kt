package b.varchenko.paymentapp.ui.screen.purchase

sealed class PurchaseScreenEvent {
    data object InvalidAmount : PurchaseScreenEvent()
    data object NoInternetConnection : PurchaseScreenEvent()
    data object Error : PurchaseScreenEvent()

    data class OpenReceiptScreen(val id: String) : PurchaseScreenEvent()
}