package com.warrantysafe.app.data.repository

import com.warrantysafe.app.domain.model.Recent
import com.warrantysafe.app.domain.repository.RecentRepository

class RecentRepositoryImpl: RecentRepository {
   // private val recentList = mutableListOf<Recent>()
    private val recentList = mutableListOf<Recent>(
       Recent("recent1"),
       Recent("recent2"),
       Recent("recent3"),
       Recent("recent4")
        )
    override suspend fun getRecents(): List<Recent> {
        return recentList
    }
}