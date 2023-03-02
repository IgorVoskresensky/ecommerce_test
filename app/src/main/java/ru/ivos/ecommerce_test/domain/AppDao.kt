package ru.ivos.ecommerce_test.domain

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.ivos.ecommerce_test.domain.models.local.Favorite
import ru.ivos.ecommerce_test.domain.models.local.User

@Dao
interface AppDao {

    @Query("SELECT * FROM users ORDER BY id")
    suspend fun getUsers() : List<User>

    @Query("SELECT * FROM users WHERE firstName = :name")
    suspend fun getUser(name: String) : User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("DELETE FROM users WHERE id = :id")
    suspend fun deleteUser(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Query("DELETE FROM favorites WHERE name = :name")
    suspend fun deleteFavorite(name: String)
}