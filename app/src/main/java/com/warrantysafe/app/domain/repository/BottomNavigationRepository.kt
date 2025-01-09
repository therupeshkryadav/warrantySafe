package com.warrantysafe.app.domain.repository

import com.warrantysafe.app.domain.model.BottomNavigation

interface BottomNavigationRepository {
    suspend fun getBottomNavigationIcons(): List<BottomNavigation>
}