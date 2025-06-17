package com.example.dmshop.domain.repository

import com.example.dmshop.data.local.entity.OrderEntity
import com.example.dmshop.domain.model.CartState
import kotlinx.coroutines.flow.Flow

interface OrdersRepository {
    suspend fun placeOrder(cartState: CartState)
    fun getAllOrders(): Flow<List<OrderEntity>>
    suspend fun getOrderById(orderId: String): OrderEntity?
} 