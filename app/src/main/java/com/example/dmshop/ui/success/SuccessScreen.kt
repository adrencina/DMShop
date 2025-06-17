package com.example.dmshop.ui.success

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dmshop.ui.viewmodel.SharedCartViewModel

@Composable
fun SuccessScreen(
    navController: NavController,
    sharedVm: SharedCartViewModel
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "¡Pedido enviado!",
                style = MaterialTheme.typography.headlineMedium
            )
            Button(
                onClick = {
                    sharedVm.clearCart()
                    navController.navigate("catalog") {
                        popUpTo("catalog") { inclusive = true }
                    }
                }
            ) {
                Text("Volver al catálogo")
            }
        }
    }
} 