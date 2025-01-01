package com.warrantysafe.app.utils

import com.warrantysafe.app.presentation.navigation.Route

// Extension function to convert String? to Route?
fun String?.toRoute(): Route? {
    return when (this) {
        Route.HomeScreen.route -> Route.HomeScreen
        Route.AddScreen.route -> Route.AddScreen
        Route.ProfileScreen.route -> Route.ProfileScreen
        Route.SearchScreen.route -> Route.SearchScreen
        else -> null
    }
}