package b.varchenko.data.network.dto

import com.google.gson.annotations.SerializedName
import java.util.Date

data class RequestReceiptResponseDto(
    @SerializedName("amount")
    val amount: Amount,
    @SerializedName("status")
    val status: String,
    @SerializedName("transactionDetails")
    val transactionDetails: TransactionDetails,
    @SerializedName("transactionId")
    val transactionId: String
) {

    data class Amount(
        @SerializedName("currency")
        val currency: String,
        @SerializedName("discountAmount")
        val discountAmount: String,
        @SerializedName("purchaseAmount")
        val purchaseAmount: String,
        @SerializedName("taxRate")
        val taxRate: String,
        @SerializedName("taxableAmount")
        val taxableAmount: String,
        @SerializedName("tipAmount")
        val tipAmount: String
    )

    data class TransactionDetails(
        @SerializedName("timestamp")
        val timestamp: Date
    )
}


