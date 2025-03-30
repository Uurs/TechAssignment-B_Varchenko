package b.varchenko.paymentapp.di

import b.varchenko.data.di.NetworkAvailabilityModule
import b.varchenko.data.network.NetworkAvailability
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.coEvery
import io.mockk.mockk

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkAvailabilityModule::class],
)
class MockNetworkAvailabilityModule {

    @Provides
    fun bindNetworkAvailability(): NetworkAvailability {
        return mockNetworkAvailability
    }

    companion object {
        val mockNetworkAvailability = mockk<NetworkAvailability>()
            .apply {
                coEvery { isNetworkAvailable() } returns true
            }
    }
}