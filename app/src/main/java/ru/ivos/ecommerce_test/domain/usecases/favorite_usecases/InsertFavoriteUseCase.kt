package ru.ivos.ecommerce_test.domain.usecases.favorite_usecases

import ru.ivos.ecommerce_test.domain.repositories.local.UserDao
import ru.ivos.ecommerce_test.domain.models.local.Favorite
import ru.ivos.ecommerce_test.domain.repositories.local.FavoritesDao
import javax.inject.Inject

class InsertFavoriteUseCase @Inject constructor(
    private val favoritesDao: FavoritesDao
) {

    suspend operator fun invoke(favorite: Favorite) = favoritesDao.insertFavorite(favorite)
}