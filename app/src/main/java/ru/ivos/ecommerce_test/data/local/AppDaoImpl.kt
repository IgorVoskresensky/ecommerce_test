package ru.ivos.ecommerce_test.data.local

import ru.ivos.ecommerce_test.domain.models.local.User
import ru.ivos.ecommerce_test.domain.AppDao
import javax.inject.Inject

class AppDaoImpl @Inject constructor(
    private val appDao: AppDao
) {

    suspend fun getUsers() : List<User> = appDao.getUsers()

    suspend fun getUser(id: Int) : User = appDao.getUser(id)

    suspend fun insertUser(user: User) = appDao.insertUser(user)

    suspend fun deleteUser(id: Int) = appDao.deleteUser(id)
}