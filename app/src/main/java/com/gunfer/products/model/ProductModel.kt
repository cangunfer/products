package com.gunfer.products.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "products")
data class Product(
    @ColumnInfo(name = "product_id")
    @SerializedName("product_id")
    @PrimaryKey
    val productId: String,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name:String?,

    @ColumnInfo(name = "price")
    @SerializedName("price")
    val price: Int?,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String?,

    @ColumnInfo(name = "product_image_url")
    @SerializedName("image")
    val productImageUrl: String?) {
}

