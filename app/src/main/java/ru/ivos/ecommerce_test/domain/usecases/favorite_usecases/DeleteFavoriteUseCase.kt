package ru.ivos.ecommerce_test.domain.usecases.favorite_usecases

import ru.ivos.ecommerce_test.domain.AppDao
import ru.ivos.ecommerce_test.domain.models.local.Favorite
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val appDao: AppDao
) {

    suspend operator fun invoke(name: String) = appDao.deleteFavorite(name)
}