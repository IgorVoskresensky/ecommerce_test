package ru.ivos.ecommerce_test.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.ivos.ecommerce_test.data.models.local.User

@Dao
interface AppDao {

    @Query("SELECT * FROM users ORDER BY id")
    suspend fun getUsers() : List<User>

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUser(id: Int) : User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("DELETE FROM users WHERE id = :id")
    suspend fun deleteUser(id: Int)
}