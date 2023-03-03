package ru.ivos.ecommerce_test.data.local.dao_impl

import ru.ivos.ecommerce_test.domain.models.local.User
import ru.ivos.ecommerce_test.domain.repositories.local.UserDao
import javax.inject.Inject

class UserDaoImpl @Inject constructor(
    private val userDao: UserDao
) {

    suspend fun getUsers() : List<User> = userDao.getUsers()

    suspend fun getUser(name: String) : User = userDao.getUser(name)

    suspend fun insertUser(user: User) = userDao.insertUser(user)

    suspend fun deleteUser(id: Int) = userDao.deleteUser(id)

}