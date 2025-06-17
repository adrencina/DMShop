package com.example.dmshop.ui.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dmshop.domain.model.CartItem
import com.example.dmshop.ui.viewmodel.FormField
import com.example.dmshop.ui.viewmodel.SharedCartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController: NavController,
    sharedVm: SharedCartViewModel
) {
    val items by sharedVm.items.collectAsState()
    val form by sharedVm.form.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carrito") }
            )
        }
    ) { padding ->
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
                CustomerForm(
                    customerName = form.customerName,
                    contact = form.contact,
                    dateTime = form.dateTime,
                    address = form.address,
                    notes = form.notes,
                    onFieldChange = { field, value ->
                        sharedVm.updateForm(field, value)
                    }
                )
            }

            item {
                Button(
                    onClick = {
                        navController.navigate("confirmation")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Confirmar Pedido")
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
fun CustomerForm(
    customerName: String,
    contact: String,
    dateTime: String,
    address: String,
    notes: String,
    onFieldChange: (FormField, String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = customerName,
            onValueChange = { onFieldChange(FormField.NAME, it) },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = contact,
            onValueChange = { onFieldChange(FormField.CONTACT, it) },
            label = { Text("Contacto") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = dateTime,
            onValueChange = { onFieldChange(FormField.DATE_TIME, it) },
            label = { Text("Fecha y Hora") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = address,
            onValueChange = { onFieldChange(FormField.ADDRESS, it) },
            label = { Text("Dirección") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = notes,
            onValueChange = { onFieldChange(FormField.NOTES, it) },
            label = { Text("Notas") },
            modifier = Modifier.fillMaxWidth()
        )
    }
} 