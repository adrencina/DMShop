package com.example.dmshop.data.repository

import com.example.dmshop.data.local.dao.OrderDao
import com.example.dmshop.data.local.entity.CartItemEntity
import com.example.dmshop.data.local.entity.OrderEntity
import com.example.dmshop.domain.model.CartState
import com.example.dmshop.domain.repository.OrdersRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrdersRepositoryImpl @Inject constructor(
    private val orderDao: OrderDao,
    private val firestore: FirebaseFirestore
) : OrdersRepository {

    override suspend fun placeOrder(cartState: CartState) {
        val orderEntity = OrderEntity(
            items = cartState.items.map { item ->
                CartItemEntity(
                    productId = item.product.id,
                    productName = item.product.name,
                    size = item.size,
                    quantity = item.quantity,
                    price = item.product.price,
                    subtotal = item.subtotal
                )
            },
            customerName = cartState.customerName,
            contact = cartState.contact,
            dateTime = cartState.dateTime,
            address = cartState.address,
            notes = cartState.notes,
            total = cartState.total
        )

        // Primero guardamos en Firestore
        firestore.collection("orders")
            .document(orderEntity.id)
            .set(orderEntity)
            .await()

        // Luego cacheamos en Room
        orderDao.insertOrder(orderEntity)
    }

    override fun getAllOrders(): Flow<List<OrderEntity>> = orderDao.getAllOrders()

    override suspend fun getOrderById(orderId: String): OrderEntity? = orderDao.getOrderById(orderId)
} 