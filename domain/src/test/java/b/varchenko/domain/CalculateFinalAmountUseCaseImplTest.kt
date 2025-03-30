package b.varchenko.domain

import b.varchenko.domain.impl.CalculateFinalAmountUseCaseImpl
import b.varchenko.domain.model.PurchaseReceipt
import b.varchenko.domain.model.PurchaseReceiptDetails
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.Date


class CalculateFinalAmountUseCaseImplTest {
    @Test
    fun `calculateFinalAmount should return correct final amount`() {
        val useCase = CalculateFinalAmountUseCaseImpl()
        // Given
        val receipt = PurchaseReceipt(
            purchaseAmount = 100.0,
            taxRate = 0.10,
            taxableAmount = 90.0,
            tipAmount = 10.0,
            discountAmount = 5.0,
            status = "Success",
            transactionId = "12345",
            currency = "USD",
            date = Date(),
        )

        val expected =  PurchaseReceiptDetails(
            receipt = receipt,
            overallPaidTax = 9.0,
            overallPaidTip = 5.0,
            overallDiscount = 5.0,
            finalAmount = 86.0
        )

        // When
        val finalAmount = useCase.execute(receipt)

        // Then
        assertEquals(expected, finalAmount)
    }
}