package com.gunfer.products.repository.source

import com.gunfer.products.model.Product
import com.gunfer.products.data.dao.ProductDAO
import javax.inject.Inject


class ProductLocalRepository @Inject constructor(private val dao: ProductDAO): ProductRepositoryInterface {
    override fun getAllProducts(): List<Product> {
        return dao.getAllProducts()
    }

    override fun insertProduct(products: Product?) {
         dao.insertProduct(products)
    }

    override fun insertAll(vararg products: Product) {
         dao.insertAll(*products)
    }

    override fun getProduct(productId: String): Product {
        return dao.getProduct(productId)
    }
}