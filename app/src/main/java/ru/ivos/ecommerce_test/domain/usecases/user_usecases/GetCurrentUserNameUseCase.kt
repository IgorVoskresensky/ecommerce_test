package ru.ivos.ecommerce_test.domain.usecases.user_usecases

import ru.ivos.ecommerce_test.domain.DataStoreRepo
import javax.inject.Inject

class GetCurrentUserNameUseCase @Inject constructor(
    private val dataStoreRepo: DataStoreRepo
) {

    suspend operator fun invoke(key: String) = dataStoreRepo.getString(key)
}