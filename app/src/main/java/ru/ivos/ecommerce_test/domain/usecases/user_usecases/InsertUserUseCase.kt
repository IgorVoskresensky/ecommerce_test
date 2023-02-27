package ru.ivos.ecommerce_test.domain.usecases.user_usecases

import ru.ivos.ecommerce_test.domain.models.local.User
import ru.ivos.ecommerce_test.domain.AppDao
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private val appDao: AppDao
) {

    suspend operator fun invoke(user: User) = appDao.insertUser(user)
}