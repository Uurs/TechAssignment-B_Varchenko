package b.varchenko.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

const val KEY_BASE_URL = "key_base_url"

@Module
@InstallIn(SingletonComponent::class)
class UrlProviderModule {

    @Provides
    @Named(KEY_BASE_URL)
    fun provideBaseUrl(): String {
        return "https://jason-koala.wallee.workers.dev"
    }
}