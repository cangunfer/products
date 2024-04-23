package com.gunfer.products.data.dao

import androidx.room.*
import com.gunfer.products.model.Product


@Dao
interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg  products: Product) : List<Long>

    @Query("SELECT * FROM products")
    fun getAllProducts(): List<Product>

    @Query("SELECT * FROM products WHERE product_id = :productId")
    fun getProduct(productId: String) :Product

    @Update
    fun updateProduct(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(products: Product?) : Long

    @Query("DELETE FROM products")
    fun deleteAllProducts()

}