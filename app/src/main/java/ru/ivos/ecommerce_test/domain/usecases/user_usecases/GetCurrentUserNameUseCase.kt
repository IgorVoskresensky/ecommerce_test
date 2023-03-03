package ru.ivos.ecommerce_test.domain.usecases.user_usecases

import ru.ivos.ecommerce_test.domain.repositories.local.DataStoreRepo
import javax.inject.Inject

class GetCurrentUserNameUseCase @Inject constructor(
    private val userDao: DataStoreRepo
) {

    suspend operator fun invoke(key: String) = userDao.getString(key)
}