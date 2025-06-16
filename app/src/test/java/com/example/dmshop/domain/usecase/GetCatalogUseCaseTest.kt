/*
package com.example.dmshop.domain.usecase

import com.example.dmshop.domain.model.Product
import com.example.dmshop.domain.repository.CatalogRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

/**
 * Pruebas unitarias para el caso de uso GetCatalogUseCase.
 */
class GetCatalogUseCaseTest {

    private lateinit var useCase: GetCatalogUseCase
    private lateinit var repository: CatalogRepository

    @Before
    fun setup() {
        repository = mock()
        useCase = GetCatalogUseCase(repository)
    }

    @Test
    fun `when repository returns products, use case should return same products`() = runBlocking {
        // Given
        val products = listOf(
            Product("1", "Product 1", 10.0, "url1"),
            Product("2", "Product 2", 20.0, "url2")
        )
        whenever(repository.getProducts()).thenReturn(flowOf(products))

        // When
        val result = useCase().first()

        // Then
        assertEquals(products, result)
    }
}
*/ 