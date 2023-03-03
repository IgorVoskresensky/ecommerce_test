package ru.ivos.ecommerce_test.domain.repositories.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.ivos.ecommerce_test.domain.models.local.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users ORDER BY id")
    suspend fun getUsers() : List<User>

    @Query("SELECT * FROM users WHERE firstName = :name")
    suspend fun getUser(name: String) : User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("DELETE FROM users WHERE id = :id")
    suspend fun deleteUser(id: Int)

}