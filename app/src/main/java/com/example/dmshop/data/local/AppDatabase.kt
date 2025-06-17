package com.example.dmshop.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dmshop.data.local.converter.Converters
import com.example.dmshop.data.local.dao.OrderDao
import com.example.dmshop.data.local.dao.ProductDao
import com.example.dmshop.data.local.entity.OrderEntity
import com.example.dmshop.data.local.entity.ProductEntity

/**
 * Base de datos principal de la aplicación que utiliza Room para el almacenamiento local.
 */
@Database(
    entities = [OrderEntity::class, ProductEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao
    abstract fun productDao(): ProductDao
} 