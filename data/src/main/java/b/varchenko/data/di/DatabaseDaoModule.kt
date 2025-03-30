package b.varchenko.data.di

import b.varchenko.data.database.RoomDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal class DatabaseDaoModule {

    @Provides
    fun providePurchaseDao(db: RoomDb) = db.purchaseDao()
}