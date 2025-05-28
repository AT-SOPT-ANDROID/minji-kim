package org.sopt.at.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        val USER_ID = intPreferencesKey("userId")
    }

    val userIdFlow: Flow<Long> = dataStore.data.map { prefs ->
        prefs[USER_ID]?.toLong() ?: -1L
    }

    suspend fun saveUserId(userId: Int) {
        dataStore.edit { prefs -> prefs[USER_ID] = userId }
    }
}
