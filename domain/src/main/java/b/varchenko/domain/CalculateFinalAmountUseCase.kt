package b.varchenko.domain

import b.varchenko.domain.model.PurchaseReceipt
import b.varchenko.domain.model.PurchaseReceiptDetails

interface CalculateFinalAmountUseCase {
    fun execute(receipt: PurchaseReceipt): PurchaseReceiptDetails
}