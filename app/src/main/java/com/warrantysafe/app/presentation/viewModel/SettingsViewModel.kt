package com.warrantysafe.app.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.warrantysafe.app.domain.model.Settings
import com.warrantysafe.app.domain.useCases.GetSettingsUseCase
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val getSettingsUseCase: GetSettingsUseCase
): ViewModel() {

    // State holders for all, active, and expired products
    var settingsTexts = mutableStateOf<List<Settings>>(mutableListOf())

    // Load all products
    fun loadSettings() {
        viewModelScope.launch {
            val fetchedSettings = getSettingsUseCase()
            settingsTexts.value = fetchedSettings
        }
    }
}