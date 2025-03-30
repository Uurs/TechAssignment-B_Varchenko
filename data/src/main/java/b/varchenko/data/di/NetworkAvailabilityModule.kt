package b.varchenko.data.di

import androidx.annotation.VisibleForTesting
import b.varchenko.data.network.NetworkAvailability
import b.varchenko.data.network.NetworkAvailabilityImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@VisibleForTesting
@Module
@InstallIn(SingletonComponent::class)
class NetworkAvailabilityModule {

    @Provides
    fun bindNetworkAvailability(): NetworkAvailability {
        return NetworkAvailabilityImpl()
    }
}