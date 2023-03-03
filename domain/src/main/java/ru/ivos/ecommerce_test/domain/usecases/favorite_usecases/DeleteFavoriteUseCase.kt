package ru.ivos.ecommerce_test.domain.usecases.favorite_usecases

import ru.ivos.ecommerce_test.domain.repositories.local.FavoritesDao
import ru.ivos.ecommerce_test.domain.repositories.local.UserDao
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val favoritesDao: FavoritesDao
) {

    suspend operator fun invoke(name: String) = favoritesDao.deleteFavorite(name)
}