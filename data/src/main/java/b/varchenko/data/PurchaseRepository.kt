package b.varchenko.data

import b.varchenko.data.dto.PurchaseReceiptDto

interface PurchaseRepository {
    suspend fun getPurchaseReceipt(id: String): PurchaseReceiptDto?

    suspend fun doPurchaseRequest(amount: Double): PurchaseReceiptDto

    suspend fun savePurchase(dto: PurchaseReceiptDto)
}