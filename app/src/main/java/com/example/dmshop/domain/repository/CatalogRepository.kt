package com.example.dmshop.domain.repository

import com.example.dmshop.domain.model.Product
import kotlinx.coroutines.flow.Flow

/**
 * Interfaz que define las operaciones del repositorio de catálogo.
 */
interface CatalogRepository {
    fun getProducts(): Flow<List<Product>>
    suspend fun refreshProducts()
} 