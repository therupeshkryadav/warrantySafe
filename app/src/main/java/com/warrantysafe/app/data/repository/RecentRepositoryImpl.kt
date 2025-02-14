package com.warrantysafe.app.data.repository

import com.warrantysafe.app.domain.model.Recent
import com.warrantysafe.app.domain.repository.RecentRepository

class RecentRepositoryImpl: RecentRepository {
   // private val recentList = mutableListOf<Recent>()
    private val recentList = mutableListOf<Recent>(
        Recent("1"),
        Recent("2"),
        Recent("3"),
        Recent("4")
    )

    override suspend fun getRecents(): List<Recent> {
        return recentList
    }
}