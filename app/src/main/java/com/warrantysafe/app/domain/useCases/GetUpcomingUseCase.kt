package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.Upcoming
import com.warrantysafe.app.domain.repository.UpcomingRepository

class GetUpcomingUseCase(private val upcomingRepository: UpcomingRepository) {
    suspend operator fun invoke(): List<Upcoming> {
        return upcomingRepository.getUpcomingNotifications()
    }
}