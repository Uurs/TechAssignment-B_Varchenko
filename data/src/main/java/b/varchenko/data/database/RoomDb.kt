package b.varchenko.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import b.varchenko.data.database.dao.PurchaseDao
import b.varchenko.data.database.entity.PurchaseReceiptEntity

@Database(
    entities = [PurchaseReceiptEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class RoomDb: RoomDatabase() {

    internal abstract fun purchaseDao(): PurchaseDao

    companion object {
        const val DB_NAME = "app_db"
    }
}