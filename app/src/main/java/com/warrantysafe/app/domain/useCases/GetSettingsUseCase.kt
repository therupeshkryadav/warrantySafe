package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.Settings
import com.warrantysafe.app.domain.repository.SettingsRepository

class GetSettingsUseCase(private val settingsRepository: SettingsRepository) {
    suspend operator fun invoke(): List<Settings> {
        return settingsRepository.getSettings()
    }
}