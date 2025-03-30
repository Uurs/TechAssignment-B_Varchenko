package b.varchenko.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchase_receipt")
internal data class PurchaseReceiptEntity(
    @PrimaryKey @ColumnInfo("transaction_id") val transactionId: String,
    @ColumnInfo("status") val status: String,
    @ColumnInfo("currency") val currency: String,
    @ColumnInfo("discount_amount") val discountAmount: Double,
    @ColumnInfo("purchase_amount") val purchaseAmount: Double,
    @ColumnInfo("tax_rate") val taxRate: Double,
    @ColumnInfo("taxable_amount") val taxableAmount: Double,
    @ColumnInfo("tip_amount") val tipAmount: Double,
    @ColumnInfo("timestamp") val timestamp: Long,
)