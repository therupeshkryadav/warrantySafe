package com.warrantysafe.app.data.repository

import com.warrantysafe.app.domain.model.Settings
import com.warrantysafe.app.domain.repository.SettingsRepository

class SettingsRepositoryImpl: SettingsRepository {
    // private val settingsList = mutableListOf<Settings>()
    private val settingsList = mutableListOf<Settings>(
        Settings("Manage Categories"),
        Settings("Change Password"),
    )

    override suspend fun getSettings(): List<Settings> {
        return settingsList
    }
}