package com.example.dmshop.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dmshop.domain.model.Product
import com.example.dmshop.ui.screens.catalog.CatalogScreen
import com.example.dmshop.ui.screens.catalog.CatalogViewModel

/**
 * Objeto que define las rutas de navegación de la aplicación.
 */
object Destinations {
    const val CATALOG = "catalog"
    const val CART = "cart"
    const val ORDERS = "orders"
    const val SETTINGS = "settings"
    const val SHOPPING_LIST = "shopping_list"
    const val CONFIRMATION = "confirmation"
}

/**
 * Grafo de navegación principal de la aplicación.
 */
@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = Destinations.CATALOG
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Destinations.CATALOG) {
            val viewModel: CatalogViewModel = hiltViewModel()
            val products by viewModel.products.collectAsState()
            val isLoading by viewModel.isLoading.collectAsState()

            CatalogScreen(
                navController = navController,
                products = products,
                isLoading = isLoading,
                onProductClick = { product ->
                    navController.navigate(Destinations.CART)
                }
            )
        }
        composable(Destinations.CART) {
            CatalogScreen(
                navController = navController,
                products = listOf(
                    Product("1", "Producto en carrito", 99.99, "url1"),
                    Product("2", "Otro producto", 149.99, "url2")
                ),
                isLoading = false,
                onProductClick = { }
            )
        }
        composable(Destinations.ORDERS) {
            CatalogScreen(
                navController = navController,
                products = listOf(
                    Product("1", "Orden #123", 299.99, "url1"),
                    Product("2", "Orden #124", 199.99, "url2")
                ),
                isLoading = false,
                onProductClick = { }
            )
        }
        composable(Destinations.SETTINGS) {
            CatalogScreen(
                navController = navController,
                products = listOf(
                    Product("1", "Configuración", 0.0, "url1"),
                    Product("2", "Perfil", 0.0, "url2")
                ),
                isLoading = false,
                onProductClick = { }
            )
        }
        composable(Destinations.SHOPPING_LIST) {
            CatalogScreen(
                navController = navController,
                products = listOf(
                    Product("1", "Lista de compras", 0.0, "url1"),
                    Product("2", "Productos guardados", 0.0, "url2")
                ),
                isLoading = false,
                onProductClick = { }
            )
        }
        composable(Destinations.CONFIRMATION) {
            CatalogScreen(
                navController = navController,
                products = listOf(
                    Product("1", "Confirmación de compra", 0.0, "url1"),
                    Product("2", "Detalles de envío", 0.0, "url2")
                ),
                isLoading = false,
                onProductClick = { }
            )
        }
    }
} 