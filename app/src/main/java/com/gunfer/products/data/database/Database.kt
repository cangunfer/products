package com.gunfer.products.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gunfer.products.data.dao.ProductDAO
import com.gunfer.products.model.Product
import com.gunfer.products.util.Constants

@androidx.room.Database(entities = arrayOf(Product::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDAO
}