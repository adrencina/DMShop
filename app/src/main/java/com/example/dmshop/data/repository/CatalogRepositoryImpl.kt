package com.example.dmshop.data.repository

import com.example.dmshop.data.local.dao.ProductDao
import com.example.dmshop.data.local.entity.ProductEntity
import com.example.dmshop.domain.model.Product
import com.example.dmshop.domain.repository.CatalogRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementación del repositorio de catálogo que sincroniza datos entre Firestore y Room.
 */
@Singleton
class CatalogRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val firestore: FirebaseFirestore
) : CatalogRepository {

    override fun getProducts(): Flow<List<Product>> {
        return productDao.getAll().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun refreshProducts() {
        // TODO: Implementar lógica de sincronización entre Firestore y Room
    }

    private fun ProductEntity.toDomain(): Product {
        return Product(
            id = id,
            name = name,
            price = price,
            imageUrl = imageUrl
        )
    }
} 