package ru.ivos.ecommerce_test.data.remote

import ru.ivos.ecommerce_test.domain.repositories.remote.ApiRepo
import ru.ivos.ecommerce_test.domain.models.remote.Details
import ru.ivos.ecommerce_test.domain.models.remote.FlashSaleList
import ru.ivos.ecommerce_test.domain.models.remote.LatestList
import ru.ivos.ecommerce_test.domain.models.remote.Words
import javax.inject.Inject

class ApiRepoImpl @Inject constructor(
    private val apiRepo: ApiRepo
) {

    suspend fun getFlashSale() : FlashSaleList = apiRepo.getFlashSale()

    suspend fun getLatest() : LatestList = apiRepo.getLatest()

    suspend fun getDetails() : Details = apiRepo.getDetails()

    suspend fun getBrands() : Words = apiRepo.getBrands()
}