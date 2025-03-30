package b.varchenko.domain.impl

import b.varchenko.domain.CalculateFinalAmountUseCase
import b.varchenko.domain.model.PurchaseReceipt
import b.varchenko.domain.model.PurchaseReceiptDetails
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

internal class CalculateFinalAmountUseCaseImpl @Inject constructor() : CalculateFinalAmountUseCase {

    override fun execute(receipt: PurchaseReceipt): PurchaseReceiptDetails {
        var overallPaidTax = BigDecimal(receipt.taxableAmount).multiply(BigDecimal(receipt.taxRate))
        var overallPaidTip = (BigDecimal(receipt.tipAmount).minus(
            BigDecimal(receipt.tipAmount).minus(BigDecimal(receipt.discountAmount))
        ))
        var finalAmount =
            BigDecimal(receipt.purchaseAmount).minus(overallPaidTax).minus(overallPaidTip)

        overallPaidTax = overallPaidTax.setScale(2, RoundingMode.HALF_EVEN)
        overallPaidTip = overallPaidTip.setScale(2, RoundingMode.HALF_EVEN)
        finalAmount = finalAmount.setScale(2, RoundingMode.HALF_EVEN)

        return PurchaseReceiptDetails(
            receipt = receipt,
            overallPaidTax = overallPaidTax.toDouble(),
            overallPaidTip = overallPaidTip.toDouble(),
            overallDiscount = receipt.discountAmount,
            finalAmount = finalAmount.toDouble(),
        )
    }
}