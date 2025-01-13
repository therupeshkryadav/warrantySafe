package com.warrantysafe.app.domain.repository

import com.warrantysafe.app.domain.model.Settings

interface SettingsRepository {
    suspend fun getSettings(): List<Settings>
}