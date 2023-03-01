package ru.ivos.ecommerce_test.domain

interface DataStoreRepo {

    suspend fun putBoolean(key: String, value: Boolean)

    suspend fun getBoolean(key: String): Boolean?
}