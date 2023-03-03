package ru.ivos.ecommerce_test.domain.repositories.local

interface DataStoreRepo {

    suspend fun putBoolean(key: String, value: Boolean)

    suspend fun getBoolean(key: String): Boolean?

    suspend fun putString(key: String, value: String)

    suspend fun getString(key: String): String?
}