package com.example.dmshop.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dmshop.data.local.dao.ProductDao
import com.example.dmshop.data.local.entity.ProductEntity

/**
 * Base de datos principal de la aplicación que utiliza Room para el almacenamiento local.
 */
@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
} 