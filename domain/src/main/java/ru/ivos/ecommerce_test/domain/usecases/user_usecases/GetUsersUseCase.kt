package ru.ivos.ecommerce_test.domain.usecases.user_usecases

import ru.ivos.ecommerce_test.domain.repositories.local.UserDao
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userDao: UserDao
) {

    suspend operator fun invoke() = userDao.getUsers()
}