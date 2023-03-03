package ru.ivos.ecommerce_test.domain.usecases.api_usecases

import ru.ivos.ecommerce_test.domain.repositories.remote.ApiRepo
import ru.ivos.ecommerce_test.domain.models.remote.Latest
import javax.inject.Inject

class GetLatestUseCase @Inject constructor(
    private val apiRepo: ApiRepo
) {

    suspend operator fun invoke(): List<Latest> = apiRepo.getLatest().latestList
}