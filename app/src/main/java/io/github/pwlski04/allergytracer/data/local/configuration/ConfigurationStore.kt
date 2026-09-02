package io.github.pwlski04.allergytracer.data.local.configuration

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ConfigurationStore(private val store: DataStore<Preferences>) {

    // Disclaimer Text
    private val key = booleanPreferencesKey("disclaimer_accepted")
    val disclaimerAccepted: Flow<Boolean> = store.data.map { prefs ->
        prefs[key] ?: false
    }
    suspend fun setDisclaimerAccepted(value: Boolean) {
        store.edit { prefs ->
            prefs[key] = value
        }
    }
}