package com.example.dmshop.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dmshop.domain.model.CartState
import java.util.Date

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey
    val id: String = java.util.UUID.randomUUID().toString(),
    val items: List<CartItemEntity>,
    val customerName: String,
    val contact: String,
    val dateTime: String,
    val address: String,
    val notes: String,
    val total: Double,
    val createdAt: Date = Date(),
    val status: String = "PENDING"
)

data class CartItemEntity(
    val productId: String,
    val productName: String,
    val size: String,
    val quantity: Int,
    val price: Double,
    val subtotal: Double
) 