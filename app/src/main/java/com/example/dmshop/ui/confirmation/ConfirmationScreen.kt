package com.example.dmshop.ui.confirmation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dmshop.domain.model.CartItem
import com.example.dmshop.ui.viewmodel.SharedCartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmationScreen(
    navController: NavController,
    sharedVm: SharedCartViewModel,
    viewModel: ConfirmationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val items by sharedVm.items.collectAsState()
    val form by sharedVm.form.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Confirmar Pedido") }
            )
        }
    ) { padding ->
        when (uiState) {
            is ConfirmationUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is ConfirmationUiState.Success -> {
                sharedVm.clearCart()
                navController.navigate("success")
            }
            is ConfirmationUiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text((uiState as ConfirmationUiState.Error).message)
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(items) { item ->
                        CartItemCard(item)
                    }

                    item {
                        CustomerInfoCard(form)
                    }

                    item {
                        val total = items.sumOf { it.subtotal }
                        Text(
                            text = "Total: $$total",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }

                    item {
                        Button(
                            onClick = { 
                                viewModel.submitOrder(sharedVm.currentCart())
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Enviar Pedido")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CartItemCard(item: CartItem) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = item.product.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Talla: ${item.size}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Cantidad: ${item.quantity}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Subtotal: $${item.subtotal}",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun CustomerInfoCard(form: com.example.dmshop.domain.model.CartForm) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Datos del Cliente",
                style = MaterialTheme.typography.titleMedium
            )
            Text("Nombre: ${form.customerName}")
            Text("Contacto: ${form.contact}")
            Text("Fecha y Hora: ${form.dateTime}")
            Text("Dirección: ${form.address}")
            if (form.notes.isNotEmpty()) {
                Text("Notas: ${form.notes}")
            }
        }
    }
} 