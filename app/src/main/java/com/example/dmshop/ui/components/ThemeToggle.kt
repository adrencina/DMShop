package com.example.dmshop.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.dmshop.ui.theme.ThemeViewModel

@Composable
fun ThemeToggle(
    themeViewModel: ThemeViewModel,
    modifier: Modifier = Modifier
) {
    val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()

    IconButton(
        onClick = { themeViewModel.toggleTheme() },
        modifier = modifier
    ) {
        Icon(
            imageVector = if (isDarkTheme) Icons.Default.LightMode else Icons.Default.DarkMode,
            contentDescription = if (isDarkTheme) "Cambiar a tema claro" else "Cambiar a tema oscuro"
        )
    }
} 