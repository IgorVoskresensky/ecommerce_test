package ru.ivos.ecommerce_test.data.models.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataWords(
    @SerializedName("words")
    @Expose
    val words: List<String>
)