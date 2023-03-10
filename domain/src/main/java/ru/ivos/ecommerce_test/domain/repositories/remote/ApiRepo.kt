package ru.ivos.ecommerce_test.domain.repositories.remote

import retrofit2.http.GET
import ru.ivos.ecommerce_test.domain.models.remote.Details
import ru.ivos.ecommerce_test.domain.models.remote.FlashSaleList
import ru.ivos.ecommerce_test.domain.models.remote.LatestList
import ru.ivos.ecommerce_test.domain.models.remote.Words

interface ApiRepo {

    @GET("a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getFlashSale() : FlashSaleList

    @GET("cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatest() : LatestList

    @GET("f7f99d04-4971-45d5-92e0-70333383c239")
    suspend fun getDetails() : Details

    @GET("4c9cd822-9479-4509-803d-63197e5a9e19")
    suspend fun getBrands() : Words
}