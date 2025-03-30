package b.varchenko.domain.impl

import b.varchenko.data.PurchaseRepository
import b.varchenko.domain.CalculateFinalAmountUseCase
import b.varchenko.domain.GetPurchaseReceiptDetailsUseCase
import b.varchenko.domain.model.PurchaseReceipt
import b.varchenko.domain.model.PurchaseReceiptDetails
import b.varchenko.domain.model.PurchaseReceiptDetailsError
import b.varchenko.domain.model.PurchaseRequestError
import b.varchenko.domain.model.UseCaseResult
import b.varchenko.domain.model.mapping.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class GetPurchaseReceiptDetailsUseCaseImpl @Inject constructor(
    private val purchaseRepository: PurchaseRepository,
    private val calculateFinalAmountUseCase: CalculateFinalAmountUseCase,
) : GetPurchaseReceiptDetailsUseCase {

    override suspend fun execute(
        id: String
    ): UseCaseResult<PurchaseReceiptDetails, PurchaseReceiptDetailsError> {
        return try {
            val receipt = withContext(Dispatchers.IO) {
                 purchaseRepository.getPurchaseReceipt(id)
            }
            if (receipt != null) {
                UseCaseResult(data = getDetails(receipt.toDomain()))
            } else {
                UseCaseResult(error = PurchaseReceiptDetailsError.NotFound)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            UseCaseResult(error = PurchaseReceiptDetailsError.RequestError)
        }
    }

    private fun getDetails(receipt: PurchaseReceipt): PurchaseReceiptDetails {
        return calculateFinalAmountUseCase.execute(receipt)
    }
}