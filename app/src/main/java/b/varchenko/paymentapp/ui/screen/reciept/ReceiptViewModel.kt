package b.varchenko.paymentapp.ui.screen.reciept

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import b.varchenko.domain.GetPurchaseReceiptDetailsUseCase
import b.varchenko.domain.utils.onError
import b.varchenko.domain.utils.onSuccess
import b.varchenko.paymentapp.ReceiptDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReceiptViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getPurchaseReceiptDetailsUseCase: GetPurchaseReceiptDetailsUseCase,
) : ViewModel() {
    val receiptId = savedStateHandle.toRoute<ReceiptDestination>().id

    private val _state = MutableStateFlow(ReceiptViewState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getPurchaseReceiptDetailsUseCase.execute(receiptId)
                .onSuccess { receipt ->
                    _state.update {
                        it.copy(
                            details = ReceiptViewState.Details(
                                isSuccess = receipt.receipt.isSuccess,
                                transactionId = receipt.receipt.transactionId,
                                purchaseAmount = receipt.receipt.purchaseAmount,
                                currency = receipt.receipt.currency,
                                overallPaidTax = receipt.overallPaidTax,
                                overallPaidTip = receipt.overallPaidTip,
                                overallDiscount = receipt.overallDiscount,
                                finalAmount = receipt.finalAmount,
                            ),
                            isLoading = false,
                        )
                    }
                }
                .onError {
                    _state.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                }
        }
    }
}