package b.varchenko.paymentapp.ui.screen.purchase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import b.varchenko.domain.PurchaseUseCase
import b.varchenko.domain.model.PurchaseRequestError
import b.varchenko.domain.utils.onError
import b.varchenko.domain.utils.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

@HiltViewModel
class PurchaseViewModel @Inject constructor(
    private val purchaseUseCase: PurchaseUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(PurchaseViewState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<PurchaseScreenEvent>()
    val event = _event.asSharedFlow()

    fun onPinPadValuePress(value: Int) {
        val newAmount = BigDecimal(state.value.purchaseAmount)
            .multiply(BigDecimal(10))
            .add(BigDecimal(value).divide(BigDecimal(100)))
            .setScale(2, RoundingMode.HALF_EVEN)
            .toDouble()
        if (newAmount > 9999999.99) return
        _state.update {
            it.copy(
                purchaseAmount = newAmount
            )
        }
    }

    fun onConfirm() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val result = purchaseUseCase.execute(state.value.purchaseAmount)
            result.onSuccess {
                    _event.emit(PurchaseScreenEvent.OpenReceiptScreen(it.transactionId))
                    delay(500)
                    _state.update { it.copy(isLoading = false, purchaseAmount = 0.0) }
                }
                .onError {
                    when (it) {
                        is PurchaseRequestError.InvalidAmount ->
                            _event.emit(PurchaseScreenEvent.InvalidAmount)

                        is PurchaseRequestError.NetworkError ->
                            _event.emit(PurchaseScreenEvent.NoInternetConnection)

                        is PurchaseRequestError.RequestError ->
                            _event.emit(PurchaseScreenEvent.Error)
                    }
                    delay(500)
                    _state.update { it.copy(isLoading = false) }
                }
        }
    }
}