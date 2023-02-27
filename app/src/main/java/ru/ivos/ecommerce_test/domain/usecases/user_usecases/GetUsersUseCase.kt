package ru.ivos.ecommerce_test.domain.usecases.user_usecases

import ru.ivos.ecommerce_test.domain.AppDao
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val appDao: AppDao
) {

    suspend operator fun invoke() = appDao.getUsers()
}