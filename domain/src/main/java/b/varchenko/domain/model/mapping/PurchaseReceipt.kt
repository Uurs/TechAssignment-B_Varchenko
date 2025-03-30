package b.varchenko.domain.model.mapping

import b.varchenko.data.dto.PurchaseReceiptDto
import b.varchenko.domain.model.PurchaseReceipt
import java.util.Date

internal fun PurchaseReceiptDto.toDomain(): PurchaseReceipt {
    return PurchaseReceipt(
        currency = amount.currency,
        discountAmount = amount.discountAmount,
        purchaseAmount = amount.purchaseAmount,
        taxRate = amount.taxRate,
        taxableAmount = amount.taxableAmount,
        tipAmount = amount.tipAmount,
        status = status,
        transactionId = transactionId,
        date = transactionDetails.timestamp,
    )
}
