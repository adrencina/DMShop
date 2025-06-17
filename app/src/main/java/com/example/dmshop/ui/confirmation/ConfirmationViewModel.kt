package com.example.dmshop.ui.confirmation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmshop.domain.model.CartState
import com.example.dmshop.domain.usecase.PlaceOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class ConfirmationUiState {
    object Initial : ConfirmationUiState()
    object Loading : ConfirmationUiState()
    object Success : ConfirmationUiState()
    data class Error(val message: String) : ConfirmationUiState()
}

@HiltViewModel
class ConfirmationViewModel @Inject constructor(
    private val placeOrderUseCase: PlaceOrderUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ConfirmationUiState>(ConfirmationUiState.Initial)
    val uiState: StateFlow<ConfirmationUiState> = _uiState

    fun submitOrder(cartState: CartState) {
        viewModelScope.launch {
            _uiState.value = ConfirmationUiState.Loading
            placeOrderUseCase(cartState)
                .onSuccess {
                    _uiState.value = ConfirmationUiState.Success
                }
                .onFailure { error ->
                    _uiState.value = ConfirmationUiState.Error(error.message ?: "Error desconocido")
                }
        }
    }
} 