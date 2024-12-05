package com.warrantysafe.app.presentation.navgraph

sealed class Route(
    val route : String
){
    object SplashSheet : Route(route = "splashSheet")
    object HomeScreen : Route(route = "homeScreen")
    object SearchScreen : Route(route = "search_screen")
    object AddScreen : Route(route = "addScreen")
    object ProfileScreen : Route(route = "profileScreen")
    object ProductDetailsScreen : Route(route = "productDetailsScreen")
    object NotificationScreen : Route(route = "notificationScreen")
    object BottomNavigation : Route(route = "bottomNavigation")
}