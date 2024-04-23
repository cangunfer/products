package com.gunfer.products.repository.source

import com.gunfer.products.model.Product
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject
import javax.inject.Singleton

@ActivityRetainedScoped
interface ProductRepositoryInterface {
    fun getAllProducts(): List<Product>
    fun insertProduct(products: Product?)
    fun insertAll(vararg  products: Product)
    fun getProduct(productId: String) :Product
}