package b.varchenko.data.di

import b.varchenko.data.PurchaseRepository
import b.varchenko.data.impl.PurchaseRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindPurchaseRepository(
        purchaseRepositoryImpl: PurchaseRepositoryImpl,
    ): PurchaseRepository
}