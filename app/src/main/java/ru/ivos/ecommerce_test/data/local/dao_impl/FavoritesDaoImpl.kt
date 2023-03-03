package ru.ivos.ecommerce_test.data.local.dao_impl

import ru.ivos.ecommerce_test.domain.models.local.Favorite
import ru.ivos.ecommerce_test.domain.repositories.local.FavoritesDao
import javax.inject.Inject

class FavoritesDaoImpl @Inject constructor(
    private val favoritesDao: FavoritesDao
){

    suspend fun insertFavorite(favorite: Favorite) = favoritesDao.insertFavorite(favorite)

    suspend fun deleteFavorite(name: String) = favoritesDao.deleteFavorite(name)
}