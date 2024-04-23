package com.gunfer.products.repository.source

import com.gunfer.products.data.dao.ProductDAO
import com.gunfer.products.model.Product
import com.gunfer.products.model.ProductResponse
import com.gunfer.products.service.ProductAPI
import com.gunfer.products.service.ProductAPIService
import retrofit2.Response
import javax.inject.Inject

class ProductApiRepository @Inject constructor(private val productAPI: ProductAPI) {
    suspend fun getAllProducts(): Response<ProductResponse> {
        return productAPI.getProducts()
    }

    suspend fun getProduct(productId: String): Response<Product> {
        return productAPI.getProduct(productId)
    }
}