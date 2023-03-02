package ru.ivos.ecommerce_test.data.local

import ru.ivos.ecommerce_test.domain.models.local.User
import ru.ivos.ecommerce_test.domain.AppDao
import ru.ivos.ecommerce_test.domain.models.local.Favorite
import javax.inject.Inject

class AppDaoImpl @Inject constructor(
    private val appDao: AppDao
) {

    suspend fun getUsers() : List<User> = appDao.getUsers()

    suspend fun getUser(name: String) : User = appDao.getUser(name)

    suspend fun insertUser(user: User) = appDao.insertUser(user)

    suspend fun deleteUser(id: Int) = appDao.deleteUser(id)

    suspend fun insertFavorite(favorite: Favorite) = appDao.insertFavorite(favorite)

    suspend fun deleteFavorite(name: String) = appDao.deleteFavorite(name)
}