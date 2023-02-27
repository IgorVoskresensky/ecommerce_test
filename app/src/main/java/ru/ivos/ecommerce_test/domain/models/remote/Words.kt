package ru.ivos.ecommerce_test.domain.models.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Words(
    @SerializedName("words")
    @Expose
    val words: List<String>
)