package ru.ivos.ecommerce_test.domain.models.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Details(
    @SerializedName("name")
    @Expose
    val name: String = "",
    @SerializedName("description")
    @Expose
    val description: String = "",
    @SerializedName("rating")
    @Expose
    val rating: Double = 0.0,
    @SerializedName("number_of_reviews")
    @Expose
    val numberOfReviews: Int = 0,
    @SerializedName("price")
    @Expose
    val price: Int = 0,
    @SerializedName("colors")
    @Expose
    val colors: List<String>,
    @SerializedName("image_urls")
    @Expose
    val imageUrls: List<String>,
)