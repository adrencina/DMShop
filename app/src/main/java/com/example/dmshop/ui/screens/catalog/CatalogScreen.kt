package com.example.dmshop.ui.screens.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dmshop.domain.model.Product
import com.example.dmshop.ui.components.ThemeToggle
import com.example.dmshop.ui.theme.ThemeViewModel
import com.example.dmshop.ui.viewmodel.SharedCartViewModel

private val sampleProducts = listOf(
    Product(
        id = "1",
        name = "Camiseta Básica",
        description = "Camiseta 100% algodón, cómoda y versátil para el día a día.",
        price = 19.99,
        imageUrl = "https://example.com/tshirt.jpg",
        availableSizes = listOf("S", "M", "L", "XL")
    ),
    Product(
        id = "2",
        name = "Jeans Clásicos",
        description = "Jeans de corte recto, perfectos para cualquier ocasión.",
        price = 49.99,
        imageUrl = "https://example.com/jeans.jpg",
        availableSizes = listOf("28", "30", "32", "34")
    ),
    Product(
        id = "3",
        name = "Zapatillas Deportivas",
        description = "Zapatillas ligeras y cómodas para actividades deportivas.",
        price = 79.99,
        imageUrl = "https://example.com/sneakers.jpg",
        availableSizes = listOf("38", "39", "40", "41", "42")
    ),
    Product(
        id = "4",
        name = "Chaqueta Ligera",
        description = "Chaqueta impermeable ideal para días lluviosos.",
        price = 89.99,
        imageUrl = "https://example.com/jacket.jpg",
        availableSizes = listOf("S", "M", "L")
    )
)

/**
 * Pantalla principal que muestra el catálogo de productos.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(
    navController: NavController,
    sharedVm: SharedCartViewModel,
    viewModel: CatalogViewModel = hiltViewModel(),
    themeViewModel: ThemeViewModel = hiltViewModel()
) {
    val products by viewModel.products.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                ),
                actions = {
                    ThemeToggle(themeViewModel = themeViewModel)
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(sampleProducts) { product ->
                    ProductCard(
                        product = product,
                        onAddToCart = { size ->
                            sharedVm.addItem(product, size, 1)
                            navController.navigate("cart")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    onAddToCart: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingBag,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "$${product.price}",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                product.availableSizes.forEach { size ->
                    OutlinedButton(
                        onClick = { onAddToCart(size) },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(size)
                    }
                }
            }
        }
    }
} 