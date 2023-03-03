package ru.ivos.ecommerce_test.domain.usecases.api_usecases

import ru.ivos.ecommerce_test.domain.repositories.remote.ApiRepo
import javax.inject.Inject

class GetBrandsUseCase @Inject constructor(
    private val apiRepo: ApiRepo
) {

    suspend operator fun invoke(): List<String> = apiRepo.getBrands().words
}