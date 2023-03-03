package ru.ivos.ecommerce_test.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.ivos.ecommerce_test.domain.models.local.Favorite
import ru.ivos.ecommerce_test.domain.models.local.User
import ru.ivos.ecommerce_test.domain.repositories.local.FavoritesDao
import ru.ivos.ecommerce_test.domain.repositories.local.UserDao

@Database(entities = [User::class, Favorite::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getFavoritesDao(): FavoritesDao

    companion object {
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "ecommerce_db"
                )
                    .build()
            }
            return instance!!
        }
    }
}