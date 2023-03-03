package ru.ivos.ecommerce_test.domain.usecases.user_usecases

import ru.ivos.ecommerce_test.domain.repositories.local.UserDao
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val userDao: UserDao
) {

    suspend operator fun invoke(id: Int) = userDao.deleteUser(id)
}