package com.example.dmshop.domain.model

data class CartState(
    val items: List<CartItem>,
    val customerName: String,
    val contact: String,
    val dateTime: String,
    val address: String,
    val notes: String
) {
    val total: Double
        get() = items.sumOf { it.subtotal }
} 