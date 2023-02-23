package ru.ivos.ecommerce_test.data.models.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataFlashSaleList(
    @SerializedName("flash_sale")
    @Expose
    val flashSaleList: List<DataFlashSale>
)
