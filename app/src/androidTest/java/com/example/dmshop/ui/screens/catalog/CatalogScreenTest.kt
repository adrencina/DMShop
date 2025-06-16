/*
package com.example.dmshop.ui.screens.catalog

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dmshop.domain.model.Product
import com.example.dmshop.ui.theme.DMShopTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Pruebas de instrumentación para la pantalla de catálogo.
 */
@RunWith(AndroidJUnit4::class)
class CatalogScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenScreenLoads_showsLoadingState() {
        // Given
        val products = listOf(
            Product("1", "Product 1", 10.0, "url1"),
            Product("2", "Product 2", 20.0, "url2")
        )

        // When
        composeTestRule.setContent {
            DMShopTheme {
                val navController = rememberNavController()
                CatalogScreen(
                    navController = navController,
                    products = products,
                    isLoading = true,
                    onProductClick = {}
                )
            }
        }

        // Then
        composeTestRule.onNodeWithText("Cargando...").assertExists()
    }

    @Test
    fun whenProductsLoaded_showsProductList() {
        // Given
        val products = listOf(
            Product("1", "Product 1", 10.0, "url1"),
            Product("2", "Product 2", 20.0, "url2")
        )

        // When
        composeTestRule.setContent {
            DMShopTheme {
                val navController = rememberNavController()
                CatalogScreen(
                    navController = navController,
                    products = products,
                    isLoading = false,
                    onProductClick = {}
                )
            }
        }

        // Then
        composeTestRule.onNodeWithText("Product 1").assertExists()
        composeTestRule.onNodeWithText("Product 2").assertExists()
    }

    @Test
    fun whenErrorState_showsErrorMessage() {
        // Given
        val errorMessage = "Error loading products"
        val viewModel = mock<CatalogViewModel>()
        whenever(viewModel.uiState).thenReturn(
            MutableStateFlow(CatalogUiState.Error(errorMessage))
        )

        // When
        composeTestRule.setContent {
            CatalogScreen(viewModel = viewModel)
        }

        // Then
        composeTestRule.onNodeWithText(errorMessage).assertExists()
    }
}
*/ 