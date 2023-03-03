package ru.ivos.ecommerce_test.domain.usecases.user_usecases

import ru.ivos.ecommerce_test.domain.models.local.User
import ru.ivos.ecommerce_test.domain.repositories.local.UserDao
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private val userDao: UserDao
) {

    suspend operator fun invoke(user: User) = userDao.insertUser(user)
}