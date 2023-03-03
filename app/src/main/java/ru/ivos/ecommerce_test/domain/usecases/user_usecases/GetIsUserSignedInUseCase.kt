package ru.ivos.ecommerce_test.domain.usecases.user_usecases

import ru.ivos.ecommerce_test.domain.repositories.local.DataStoreRepo
import javax.inject.Inject

class GetIsUserSignedInUseCase @Inject constructor(
    private val dataStoreRepo: DataStoreRepo
) {

    suspend operator fun invoke(key: String) = dataStoreRepo.getBoolean(key)
}