package ru.ivos.ecommerce_test.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.ivos.ecommerce_test.data.local.AppDatabase
import ru.ivos.ecommerce_test.data.local.DataStoreRepoImpl
import ru.ivos.ecommerce_test.domain.ApiRepo
import ru.ivos.ecommerce_test.domain.AppDao
import ru.ivos.ecommerce_test.domain.DataStoreRepo
import ru.ivos.ecommerce_test.utils.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseURL() = Constants.BASE_URL

    @Provides
    fun logging() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okHttpClient() = OkHttpClient.Builder()
        .addInterceptor(logging())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): ApiRepo =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()
            .create(ApiRepo::class.java)

    @Provides
    @Singleton
    fun getDatabase(context: Application): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun getDao(appDatabase: AppDatabase): AppDao {
        return appDatabase.getDao()
    }

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStoreRepo = DataStoreRepoImpl(context)
}