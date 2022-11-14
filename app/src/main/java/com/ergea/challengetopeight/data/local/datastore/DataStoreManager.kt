package com.ergea.challengetopeight.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(@ApplicationContext private val context: Context) {
    val getIsLogin: Flow<Boolean> = context.dataStore.data.map {
        it[IS_LOGIN_KEY] ?: false
    }

    val getId: Flow<Int> = context.dataStore.data.map {
        it[ID_USER_KEY] ?: 0
    }

    suspend fun setIsLogin(paramIsLogin: Boolean) {
        context.dataStore.edit {
            it[IS_LOGIN_KEY] = paramIsLogin
        }
    }

    suspend fun setId(id: Int) {
        context.dataStore.edit {
            it[ID_USER_KEY] = id
        }
    }

    suspend fun removeIsLogin() {
        context.dataStore.edit {
            it.remove(IS_LOGIN_KEY)
        }
    }

    suspend fun removeId() {
        context.dataStore.edit {
            it.remove(ID_USER_KEY)
        }
    }


    companion object {
        private const val DATASTORE_NAME = "datastore_preferences"
        private val ID_USER_KEY = intPreferencesKey("id_user_key")
        private val IS_LOGIN_KEY = booleanPreferencesKey("is_login_key")
        private val Context.dataStore by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }
}