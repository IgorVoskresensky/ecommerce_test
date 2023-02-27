package ru.ivos.ecommerce_test.domain.usecases.user_usecases

import ru.ivos.ecommerce_test.domain.AppDao
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val appDao: AppDao
) {

    suspend operator fun invoke(id: Int) = appDao.deleteUser(id)
}