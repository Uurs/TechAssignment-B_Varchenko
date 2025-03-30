package b.varchenko.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import b.varchenko.data.database.entity.PurchaseReceiptEntity

@Dao
internal interface PurchaseDao {
    @Query("SELECT * FROM purchase_receipt WHERE transaction_id = :id")
    suspend fun getPurchaseById(id: String): PurchaseReceiptEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPurchase(purchase: PurchaseReceiptEntity)
}