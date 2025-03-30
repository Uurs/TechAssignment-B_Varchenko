package b.varchenko.domain.model

sealed class PurchaseRequestError {
    data object InvalidAmount : PurchaseRequestError()
    data object NetworkError : PurchaseRequestError()
    data object RequestError : PurchaseRequestError()
}