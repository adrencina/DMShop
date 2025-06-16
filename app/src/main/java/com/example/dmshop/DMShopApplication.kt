package com.example.dmshop

import android.app.Application
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp

/**
 * Clase principal de la aplicación que inicializa Hilt para la inyección de dependencias.
 */
@HiltAndroidApp
class DMShopApplication : Application(), Configuration.Provider {

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build()
}