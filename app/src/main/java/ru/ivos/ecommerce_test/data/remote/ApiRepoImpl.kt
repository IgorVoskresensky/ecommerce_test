package ru.ivos.ecommerce_test.data.remote

import ru.ivos.ecommerce_test.data.models.remote.DataDetails
import ru.ivos.ecommerce_test.data.models.remote.DataFlashSaleList
import ru.ivos.ecommerce_test.data.models.remote.DataLatestList
import ru.ivos.ecommerce_test.data.models.remote.DataWords
import ru.ivos.ecommerce_test.domain.ApiRepo
import javax.inject.Inject

class ApiRepoImpl @Inject constructor(
    private val apiRepo: ApiRepo
) {

    suspend fun getFlashSale() : DataFlashSaleList = apiRepo.getFlashSale()

    suspend fun getLatest() : DataLatestList = apiRepo.getLatest()

    suspend fun getDetails() : DataDetails = apiRepo.getDetails()

    suspend fun getBrands() : DataWords = apiRepo.getBrands()
}