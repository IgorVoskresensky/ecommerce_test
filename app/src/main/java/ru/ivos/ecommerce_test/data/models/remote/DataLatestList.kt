package ru.ivos.ecommerce_test.data.models.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataLatestList(
    @SerializedName("latest")
    @Expose
    val latestList: List<DataLatest>
)
