package com.warrantysafe.app.presentation.navgraph

sealed class Route(
    val route : String
){
    object SplashSheet : Route(route = "splashSheet")
    object HomeScreen : Route(route = "homeScreen")
    object SearchScreen : Route(route = "searchScreen")
    object SearchNavigation : Route(route = "searchNavigation")
    object AddScreen : Route(route = "addScreen")
    object ProfileScreen : Route(route = "profileScreen")
    object SettingsScreen : Route(route = "settingsScreen")
    object AppStartNavigation : Route(route = "appStartNavigation")
    object BottomNavigation : Route(route = "bottomNavigation")
}