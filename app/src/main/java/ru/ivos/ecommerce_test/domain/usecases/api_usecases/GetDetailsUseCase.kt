package ru.ivos.ecommerce_test.domain.usecases.api_usecases

import ru.ivos.ecommerce_test.domain.repositories.remote.ApiRepo
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(
    private val apiRepo: ApiRepo
) {

    suspend operator fun invoke() = apiRepo.getDetails()
}