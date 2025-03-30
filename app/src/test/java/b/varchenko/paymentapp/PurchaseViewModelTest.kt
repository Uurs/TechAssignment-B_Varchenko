package b.varchenko.paymentapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import b.varchenko.domain.PurchaseUseCase
import b.varchenko.domain.model.PurchaseRequestError
import b.varchenko.paymentapp.di.MockNetworkAvailabilityModule
import b.varchenko.paymentapp.di.MockWebServerModule
import b.varchenko.paymentapp.di.MockWebServerModule.Companion.GET_PURCHASE_RESPONSE
import b.varchenko.paymentapp.ui.screen.purchase.PurchaseScreenEvent
import b.varchenko.paymentapp.ui.screen.purchase.PurchaseViewModel
import b.varchenko.paymentapp.ui.screen.purchase.PurchaseViewState
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.coEvery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import mockwebserver3.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@Config(
    manifest = Config.NONE,
    application = HiltTestApplication::class
)
@HiltAndroidTest
class PurchaseViewModelTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var purchaseUseCase: PurchaseUseCase

    @Before
    fun setup() {
        hiltRule.inject()
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `test initial state`() {
        val viewModel = PurchaseViewModel(purchaseUseCase)
        val initialState = viewModel.state.value

        Truth.assertThat(initialState)
            .isEqualTo(
                PurchaseViewState(
                    purchaseAmount = 0.0,
                    isLoading = false,
                )
            )
    }

    @Test
    fun `test on pin pad value press`() {
        val viewModel = PurchaseViewModel(purchaseUseCase)
        viewModel.onPinPadValuePress(5)
        viewModel.onPinPadValuePress(5)

        Truth.assertThat(viewModel.state.value)
            .isEqualTo(
                PurchaseViewState(
                    purchaseAmount = 0.55,
                    isLoading = false,
                )
            )
    }

    @Test
    fun `test on pin pad value press exceeding limit`() {
        val viewModel = PurchaseViewModel(purchaseUseCase)
        repeat(100) { viewModel.onPinPadValuePress(9) }

        Truth.assertThat(viewModel.state.value)
            .isEqualTo(
                PurchaseViewState(
                    purchaseAmount = 9999999.99,
                    isLoading = false,
                )
            )
    }

    @Test
    fun `test confirm purchase positive flow`() = runTest {
        coEvery { MockNetworkAvailabilityModule.mockNetworkAvailability.isNetworkAvailable() } returns true
        MockWebServerModule.enqueueResponseFromAsset(GET_PURCHASE_RESPONSE)

        val viewModel = PurchaseViewModel(purchaseUseCase)
        repeat(3) { viewModel.onPinPadValuePress(9) }

        viewModel.onConfirm()

        val event = async {
            try {
                viewModel.event.first()
            } catch (e: Exception) {
                null
            }
        }

        Truth.assertThat(event.await())
            .isInstanceOf(PurchaseScreenEvent.OpenReceiptScreen::class.java)
    }

    @Test
    fun `test confirm purchase server error flow`() = runTest {
        coEvery { MockNetworkAvailabilityModule.mockNetworkAvailability.isNetworkAvailable() } returns true
        MockWebServerModule.enqueueErrorResponse()

        val viewModel = PurchaseViewModel(purchaseUseCase)
        repeat(3) { viewModel.onPinPadValuePress(9) }

        viewModel.onConfirm()

        val event = async {
            try {
                viewModel.event.first()
            } catch (e: Exception) {
                null
            }
        }

        Truth.assertThat(event.await())
            .isInstanceOf(PurchaseScreenEvent.Error::class.java)
    }

    @Test
    fun `test confirm purchase invalid input error flow`() = runTest {
        coEvery { MockNetworkAvailabilityModule.mockNetworkAvailability.isNetworkAvailable() } returns true

        val viewModel = PurchaseViewModel(purchaseUseCase)

        viewModel.onConfirm()

        val event = async {
            try {
                viewModel.event.first()
            } catch (e: Exception) {
                null
            }
        }

        Truth.assertThat(event.await())
            .isInstanceOf(PurchaseScreenEvent.InvalidAmount::class.java)
    }

    @Test
    fun `test confirm purchase no internet connection flow`() = runTest {
        coEvery { MockNetworkAvailabilityModule.mockNetworkAvailability.isNetworkAvailable() } returns false

        val viewModel = PurchaseViewModel(purchaseUseCase)
        repeat(3) { viewModel.onPinPadValuePress(9) }

        viewModel.onConfirm()

        val event = async {
            try {
                viewModel.event.first()
            } catch (e: Exception) {
                null
            }
        }

        Truth.assertThat(event.await())
            .isInstanceOf(PurchaseScreenEvent.NoInternetConnection::class.java)
    }
}