package com.warrantysafe.app.domain.repository

import com.warrantysafe.app.domain.model.Recent

interface RecentRepository {
    suspend fun getRecents(): List<Recent>
}