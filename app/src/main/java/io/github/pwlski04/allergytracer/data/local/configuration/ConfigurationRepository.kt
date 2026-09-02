package io.github.pwlski04.allergytracer.data.local.configuration

import kotlinx.coroutines.flow.Flow

class ConfigurationRepository(private val store: ConfigurationStore) {
    val disclaimerAccepted: Flow<Boolean> = store.disclaimerAccepted
    suspend fun setDisclaimerAccepted(value: Boolean) = store.setDisclaimerAccepted(value)
}