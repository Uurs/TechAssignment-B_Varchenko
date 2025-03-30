package b.varchenko.domain.model

sealed class PurchaseReceiptDetailsError {
    data object NotFound : PurchaseReceiptDetailsError()
    data object RequestError : PurchaseReceiptDetailsError()
}