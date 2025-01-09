package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.Recent
import com.warrantysafe.app.domain.repository.RecentRepository

class GetRecentsUseCase(private val recentRepository: RecentRepository) {
    suspend operator fun invoke(): List<Recent> {
        return recentRepository.getRecents()
    }
}