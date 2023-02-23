package ru.ivos.ecommerce_test.data.models.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataFlashSale(
    @SerializedName("category")
    @Expose
    val category: String = "",
    @SerializedName("name")
    @Expose
    val name: String = "",
    @SerializedName("price")
    @Expose
    val price: Double = 0.0,
    @SerializedName("discount")
    @Expose
    val discount: Int = 0,
    @SerializedName("image_url")
    @Expose
    val imageUrl: String = ""
)
