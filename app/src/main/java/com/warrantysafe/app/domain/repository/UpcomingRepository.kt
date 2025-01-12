package com.warrantysafe.app.domain.repository

import com.warrantysafe.app.domain.model.Upcoming

interface UpcomingRepository {
    suspend fun getUpcomingNotifications(): List<Upcoming>
}