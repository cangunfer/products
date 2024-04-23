package com.gunfer.products.service

import com.gunfer.products.model.Product
import com.gunfer.products.model.ProductResponse
import com.gunfer.products.util.Constants.PRODUCT_BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class ProductAPIService {

    private val productApi = Retrofit.Builder()
        .baseUrl(PRODUCT_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ProductAPI::class.java)

    suspend fun getAllProducts(): Response<ProductResponse> {
        return productApi.getProducts()
    }

    suspend fun getProduct(productId: String): Response<Product> {
        return productApi.getProduct(productId)
    }

}