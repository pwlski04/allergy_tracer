package io.github.pwlski04.allergytracer.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.pwlski04.allergytracer.data.local.configuration.ConfigurationRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ConfigurationViewModel(private val repo: ConfigurationRepository): ViewModel() {
    val disclaimerAccepted: StateFlow<Boolean> = repo.disclaimerAccepted.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    var updating by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)
    fun setDisclaimerAccepted(value: Boolean){
        viewModelScope.launch {
            updating = true
            error = null
            try {
                repo.setDisclaimerAccepted(value)
            } catch(e: Exception) {
                error = e.message
            } finally {
                updating = false
            }
        }
    }
}