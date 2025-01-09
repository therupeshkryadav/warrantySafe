package com.warrantysafe.app.data.repository

import com.warrantysafe.app.R
import com.warrantysafe.app.domain.model.BottomNavigation
import com.warrantysafe.app.domain.repository.BottomNavigationRepository
import com.warrantysafe.app.presentation.navigation.Route

class BottomNavigationRepositoryImpl: BottomNavigationRepository {

    private val bottomNavigationList = mutableListOf<BottomNavigation>(
        BottomNavigation(
            icon = R.drawable.home_warranty,
            text = "Home",
            route = Route.HomeScreen
        ),
        BottomNavigation(
            icon = R.drawable.add_warranty,
            text = "Add",
            route = Route.AddScreen
        ),
        BottomNavigation(
            icon = R.drawable.profile_warranty,
            text = "Profile",
            route = Route.ProfileScreen
        )
    )

    //  private val bottomNavigationList = mutableListOf<BottomNavigation>()

    override suspend fun getBottomNavigationIcons(): List<BottomNavigation> {
        return bottomNavigationList
    }
}