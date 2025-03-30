package b.varchenko.domain.impl

import b.varchenko.data.PurchaseRepository
import b.varchenko.data.network.NetworkAvailability
import b.varchenko.domain.PurchaseUseCase
import b.varchenko.domain.model.PurchaseReceipt
import b.varchenko.domain.model.PurchaseRequestError
import b.varchenko.domain.model.UseCaseResult
import b.varchenko.domain.model.mapping.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class PurchaseUseCaseImpl @Inject constructor(
    private val purchaseRepository: PurchaseRepository,
    private val networkAvailability: NetworkAvailability,
) : PurchaseUseCase {

    override suspend fun execute(
        amount: Double
    ): UseCaseResult<PurchaseReceipt, PurchaseRequestError> {
        return withContext(Dispatchers.IO) {
            when {
                amount <= 0 ->
                    UseCaseResult(error = PurchaseRequestError.InvalidAmount)

                !networkAvailability.isNetworkAvailable() ->
                    UseCaseResult(error = PurchaseRequestError.NetworkError)

                else ->
                    executeRequest(amount)
            }
        }
    }

    private suspend fun executeRequest(
        amount: Double
    ): UseCaseResult<PurchaseReceipt, PurchaseRequestError> {
        return try {
            val result = purchaseRepository.doPurchaseRequest(amount)
            purchaseRepository.savePurchase(result)
            UseCaseResult(data = result.toDomain())
        } catch (e: Exception) {
            e.printStackTrace()
            UseCaseResult(error = PurchaseRequestError.RequestError)
        }
    }

}