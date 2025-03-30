@file:OptIn(ExperimentalOkHttpApi::class)

package b.varchenko.paymentapp.di

import b.varchenko.data.di.KEY_BASE_URL
import b.varchenko.data.di.UrlProviderModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import okhttp3.ExperimentalOkHttpApi
import javax.inject.Named


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [UrlProviderModule::class]
)
class MockWebServerModule {
    @Provides
    @Named(KEY_BASE_URL)
    fun provideUrlProvider(): String {
        return mockWebServer.url("/").toString()
    }

    companion object {
        val mockWebServer = MockWebServer()

        const val GET_PURCHASE_RESPONSE = "get_purchase_response.json"

        fun enqueueResponseFromAsset(assetsFileName: String) {
            val response = this::class.java.classLoader!!
                .getResourceAsStream("network/$assetsFileName")
                .bufferedReader()
                .use { it.readLines().joinToString("\n") }
            mockWebServer.enqueue(
                MockResponse()
                    .newBuilder()
                    .apply {
                        code = 200
                        body(response)
                    }
                    .build()
            )
        }

        fun enqueueErrorResponse() {
            mockWebServer.enqueue(
                MockResponse().newBuilder()
                    .apply {
                        code = 500
                    }
                    .build()
            )
        }
    }
}