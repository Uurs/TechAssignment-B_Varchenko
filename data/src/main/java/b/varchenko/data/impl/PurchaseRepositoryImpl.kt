package b.varchenko.data.impl

import b.varchenko.data.PurchaseRepository
import b.varchenko.data.database.dao.PurchaseDao
import b.varchenko.data.database.entity.mapping.toDto
import b.varchenko.data.database.entity.mapping.toEntity
import b.varchenko.data.dto.PurchaseReceiptDto
import b.varchenko.data.network.ReceiptApi
import b.varchenko.data.network.dto.mapping.toDto
import retrofit2.await
import javax.inject.Inject

internal class PurchaseRepositoryImpl @Inject constructor(
    private val receiptApi: ReceiptApi,
    private val purchaseDao: PurchaseDao,
) : PurchaseRepository {

    override suspend fun getPurchaseReceipt(id: String): PurchaseReceiptDto? {
        return purchaseDao.getPurchaseById(id)
            ?.toDto()
    }

    override suspend fun doPurchaseRequest(amount: Double): PurchaseReceiptDto {
        return receiptApi.getPurchase()
            .await()
            .toDto()
    }

    override suspend fun savePurchase(dto: PurchaseReceiptDto) {
        purchaseDao.insertPurchase(
            dto.toEntity()
        )
    }
}