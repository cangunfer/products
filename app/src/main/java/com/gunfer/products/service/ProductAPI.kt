package com.gunfer.products.service

import com.gunfer.products.model.Product
import com.gunfer.products.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductAPI {
    //https://s3-eu-west-1.amazonaws.com/developer-application-test/cart/list
    //https://s3-eu-west-1.amazonaws.com/developer-application-test/cart/{product_id}/detail
    @GET("list")
    suspend fun getProducts(): Response<ProductResponse>

    @GET("{product_id}/detail")
    suspend fun getProduct(@Path("product_id") id: String,): Response<Product>
}