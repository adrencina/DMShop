package com.example.dmshop.domain.model

/**
 * Modelo de dominio que representa un producto en la capa de negocio.
 */
data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val imageUrl: String
) 