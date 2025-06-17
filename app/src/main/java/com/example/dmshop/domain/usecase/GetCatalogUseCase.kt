package com.example.dmshop.domain.usecase

import com.example.dmshop.domain.model.Product
import com.example.dmshop.domain.repository.CatalogRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Caso de uso que obtiene el catálogo de productos.
 */
@Singleton
class GetCatalogUseCase @Inject constructor(
    private val repository: CatalogRepository
) {
    operator fun invoke(): Flow<List<Product>> = repository.getProducts()
} 