package b.varchenko.data.network.dto.mapping

import b.varchenko.data.dto.PurchaseReceiptDto
import b.varchenko.data.network.dto.RequestReceiptResponseDto

internal fun RequestReceiptResponseDto.toDto(): PurchaseReceiptDto {
    return PurchaseReceiptDto(
        amount = PurchaseReceiptDto.Amount(
            currency = amount.currency,
            discountAmount = amount.discountAmount.toDouble(),
            purchaseAmount = amount.purchaseAmount.toDouble(),
            taxRate = amount.taxRate.toDouble(),
            taxableAmount = amount.taxableAmount.toDouble(),
            tipAmount = amount.tipAmount.toDouble(),
        ),
        status = status,
        transactionId = transactionId,
        transactionDetails = PurchaseReceiptDto.TransactionDetails(
            timestamp = transactionDetails.timestamp
        ),
    )

}