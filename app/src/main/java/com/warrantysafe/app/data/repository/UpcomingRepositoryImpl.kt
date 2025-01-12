package com.warrantysafe.app.data.repository

import com.warrantysafe.app.domain.model.Upcoming
import com.warrantysafe.app.domain.repository.UpcomingRepository

class UpcomingRepositoryImpl: UpcomingRepository {
    // private val upcomingList = mutableListOf<Upcoming>()
    private val upcomingList = mutableListOf<Upcoming>(
        Upcoming("feature !!"),
        Upcoming("feature !!"),
        Upcoming("feature !!"),
        Upcoming("feature !!"),
        Upcoming("feature !!")
    )

    override suspend fun getUpcomingNotifications(): List<Upcoming> {
        return upcomingList
    }

}