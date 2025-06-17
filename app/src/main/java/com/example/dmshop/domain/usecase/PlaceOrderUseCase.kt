package com.example.dmshop.domain.usecase

import com.example.dmshop.domain.model.CartState
import com.example.dmshop.domain.repository.OrdersRepository
import javax.inject.Inject

class PlaceOrderUseCase @Inject constructor(
    private val repository: OrdersRepository
) {
    suspend operator fun invoke(cartState: CartState): Result<Unit> = runCatching {
        repository.placeOrder(cartState)
    }
} 