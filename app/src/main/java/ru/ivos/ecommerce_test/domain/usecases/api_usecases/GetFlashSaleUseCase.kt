package ru.ivos.ecommerce_test.domain.usecases.api_usecases

import ru.ivos.ecommerce_test.domain.ApiRepo
import ru.ivos.ecommerce_test.domain.models.remote.FlashSale
import javax.inject.Inject

class GetFlashSaleUseCase @Inject constructor(
    private val apiRepo: ApiRepo
) {

    suspend operator fun invoke(): List<FlashSale> = apiRepo.getFlashSale().flashSaleList
}