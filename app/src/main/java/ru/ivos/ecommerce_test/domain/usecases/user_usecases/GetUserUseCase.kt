package ru.ivos.ecommerce_test.domain.usecases.user_usecases

import ru.ivos.ecommerce_test.domain.repositories.local.UserDao
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userDao: UserDao
) {

    suspend operator fun invoke(name: String) = userDao.getUser(name)
}