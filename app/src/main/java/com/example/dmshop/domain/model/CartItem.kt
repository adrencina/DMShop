package com.example.dmshop.domain.model

data class CartItem(
    val product: Product,
    val size: String,
    val quantity: Int
) {
    val subtotal: Double
        get() = product.price * quantity
} 