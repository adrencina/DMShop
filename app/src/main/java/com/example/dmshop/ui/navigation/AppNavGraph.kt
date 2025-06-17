package com.example.dmshop.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dmshop.ui.cart.CartScreen
import com.example.dmshop.ui.confirmation.ConfirmationScreen
import com.example.dmshop.ui.screens.catalog.CatalogScreen
import com.example.dmshop.ui.success.SuccessScreen
import com.example.dmshop.ui.theme.ThemeViewModel
import com.example.dmshop.ui.viewmodel.SharedCartViewModel

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
    themeViewModel: ThemeViewModel,
    sharedCartViewModel: SharedCartViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = "catalog"
    ) {
        composable("catalog") {
            CatalogScreen(
                navController = navController,
                sharedVm = sharedCartViewModel,
                themeViewModel = themeViewModel
            )
        }
        
        composable("cart") { backStackEntry ->
            val sharedVm = hiltViewModel<SharedCartViewModel>(backStackEntry)
            CartScreen(navController, sharedVm)
        }
        
        composable("confirmation") { backStackEntry ->
            val sharedVm = hiltViewModel<SharedCartViewModel>(backStackEntry)
            ConfirmationScreen(navController, sharedVm)
        }
        
        composable("success") { backStackEntry ->
            val sharedVm = hiltViewModel<SharedCartViewModel>(backStackEntry)
            SuccessScreen(navController, sharedVm)
        }
    }
} 