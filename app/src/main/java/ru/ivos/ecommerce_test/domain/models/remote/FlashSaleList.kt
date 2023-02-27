package ru.ivos.ecommerce_test.domain.models.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FlashSaleList(
    @SerializedName("flash_sale")
    @Expose
    val flashSaleList: List<FlashSale>
)
