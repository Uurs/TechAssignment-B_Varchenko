package b.varchenko.domain

import b.varchenko.domain.model.PurchaseReceipt
import b.varchenko.domain.model.PurchaseRequestError
import b.varchenko.domain.model.UseCaseResult

interface PurchaseUseCase {
    suspend fun execute(amount: Double): UseCaseResult<PurchaseReceipt, PurchaseRequestError>
}