package com.gunfer.products.model

import com.google.gson.annotations.SerializedName

class ProductResponse (
    @SerializedName("products")
    val products: List<Product>,
)