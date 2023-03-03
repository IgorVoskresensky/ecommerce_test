package ru.ivos.ecommerce_test.data.local.datastore_impl

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import ru.ivos.ecommerce_test.domain.constants.Constants.DATASTORE_NAME
import ru.ivos.ecommerce_test.domain.repositories.local.DataStoreRepo

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

class DataStoreRepoImpl(
    private val context: Context
) : DataStoreRepo {

    override suspend fun putBoolean(key: String, value: Boolean) {
        val prefKey = booleanPreferencesKey(key)
        context.datastore.edit {
            it[prefKey] = value
        }
    }

    override suspend fun getBoolean(key: String): Boolean? {
        return try {
            val prefKey = booleanPreferencesKey(key)
            val preference = context.datastore.data.first()
            preference[prefKey]
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun putString(key: String, value: String) {
        val prefKey = stringPreferencesKey(key)
        context.datastore.edit {
            it[prefKey] = value
        }
    }

    override suspend fun getString(key: String): String? {
        return try {
            val prefKey = stringPreferencesKey(key)
            val preference = context.datastore.data.first()
            preference[prefKey]
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}