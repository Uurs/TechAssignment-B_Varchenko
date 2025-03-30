package b.varchenko.domain

import b.varchenko.domain.model.PurchaseReceiptDetails
import b.varchenko.domain.model.PurchaseReceiptDetailsError
import b.varchenko.domain.model.UseCaseResult

interface GetPurchaseReceiptDetailsUseCase {
    suspend fun execute(
        id: String
    ): UseCaseResult<PurchaseReceiptDetails, PurchaseReceiptDetailsError>
}