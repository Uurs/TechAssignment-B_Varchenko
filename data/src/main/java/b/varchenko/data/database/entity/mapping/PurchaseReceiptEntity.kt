package b.varchenko.data.database.entity.mapping

import b.varchenko.data.database.entity.PurchaseReceiptEntity
import b.varchenko.data.dto.PurchaseReceiptDto
import java.util.Date

internal fun PurchaseReceiptEntity.toDto() = PurchaseReceiptDto(
    amount = PurchaseReceiptDto.Amount(
        currency = currency,
        discountAmount = discountAmount,
        purchaseAmount = purchaseAmount,
        taxRate = taxRate,
        taxableAmount = taxableAmount,
        tipAmount = tipAmount,
    ),
    status = status,
    transactionId = transactionId,
    transactionDetails = PurchaseReceiptDto.TransactionDetails(
        timestamp = Date(timestamp),
    ),
)

internal fun PurchaseReceiptDto.toEntity() = PurchaseReceiptEntity(
    transactionId = transactionId,
    status = status,
    currency = amount.currency,
    discountAmount = amount.discountAmount,
    purchaseAmount = amount.purchaseAmount,
    taxRate = amount.taxRate,
    taxableAmount = amount.taxableAmount,
    tipAmount = amount.tipAmount,
    timestamp = transactionDetails.timestamp.time,
)