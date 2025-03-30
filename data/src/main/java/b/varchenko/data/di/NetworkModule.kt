package b.varchenko.data.di

import b.varchenko.data.network.ReceiptApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {
    @Provides
    fun provideRetrofit(
        @Named(KEY_BASE_URL) baseUrl: String,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideReceiptApi(retrofit: Retrofit): ReceiptApi {
        return retrofit.create(ReceiptApi::class.java)
    }
}