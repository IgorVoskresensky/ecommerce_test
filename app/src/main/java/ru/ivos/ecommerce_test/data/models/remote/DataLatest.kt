package ru.ivos.ecommerce_test.data.models.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataLatest(
    @SerializedName("category")
    @Expose
    val category: String = "",
    @SerializedName("name")
    @Expose
    val name: String = "",
    @SerializedName("price")
    @Expose
    val price: Int = 0,
    @SerializedName("image_url")
    @Expose
    val imageUrl: String = ""
)