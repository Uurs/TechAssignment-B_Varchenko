package b.varchenko.domain.di

import b.varchenko.domain.CalculateFinalAmountUseCase
import b.varchenko.domain.GetPurchaseReceiptDetailsUseCase
import b.varchenko.domain.PurchaseUseCase
import b.varchenko.domain.impl.CalculateFinalAmountUseCaseImpl
import b.varchenko.domain.impl.GetPurchaseReceiptDetailsUseCaseImpl
import b.varchenko.domain.impl.PurchaseUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface UseCaseModule {

    @Binds
    fun bindPurchaseUseCase(impl: PurchaseUseCaseImpl): PurchaseUseCase

    @Binds
    fun bindGetPurchaseReceiptUseCase(
        impl: GetPurchaseReceiptDetailsUseCaseImpl
    ): GetPurchaseReceiptDetailsUseCase

    @Binds
    fun bindCalculateFinalAmountUseCase(
        impl: CalculateFinalAmountUseCaseImpl
    ) : CalculateFinalAmountUseCase
}