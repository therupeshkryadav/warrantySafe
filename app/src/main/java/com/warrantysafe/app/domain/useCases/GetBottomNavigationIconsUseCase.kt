package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.BottomNavigation
import com.warrantysafe.app.domain.repository.BottomNavigationRepository

class GetBottomNavigationIconsUseCase(private val bottomNavigationRepository: BottomNavigationRepository) {
    suspend operator fun invoke(): List<BottomNavigation> {
        return bottomNavigationRepository.getBottomNavigationIcons()
    }
}