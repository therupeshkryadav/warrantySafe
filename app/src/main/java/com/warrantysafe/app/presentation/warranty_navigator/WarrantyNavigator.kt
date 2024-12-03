package com.warrantysafe.app.presentation.warranty_navigator

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.add.AddScreen
import com.warrantysafe.app.presentation.common.CustomTopAppBar
import com.warrantysafe.app.presentation.home.HomeScreen
import com.warrantysafe.app.presentation.navgraph.Route
import com.warrantysafe.app.presentation.profile.ProfileScreen
import com.warrantysafe.app.presentation.search.SearchScreen
import com.warrantysafe.app.presentation.warranty_navigator.components.BottomNavigationItem
import com.warrantysafe.app.presentation.warranty_navigator.components.WarrantyBottomNavigation
import com.warrantysafe.app.utils.toRoute

@Composable
fun WarrantyNavigator(
    navController: NavHostController,
    startDestination: String
) {

    val backStackState = navController.currentBackStackEntryAsState().value


    // Show bottom navigation for specific routes
    val isBottomBarVisible = backStackState?.destination?.route in listOf(
        Route.HomeScreen.route,
        Route.ProfileScreen.route
    )

    // Observe the current route for top bar logic
    val currentRoute = backStackState?.destination?.route

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.home_warranty, text = "Home", route = Route.HomeScreen),
            BottomNavigationItem(icon = R.drawable.add_warranty, text = "Add", route = Route.AddScreen),
            BottomNavigationItem(icon = R.drawable.profile_warranty, text = "Profile", route= Route.ProfileScreen)
        )
    }


    Scaffold(
        topBar = {
            currentRoute?.let {
                CustomTopAppBar(
                    navController = navController,
                    currentRoute = it
                )
            }
        },
        bottomBar = {
            if (isBottomBarVisible) {
                WarrantyBottomNavigation(
                    items = bottomNavigationItems,
                    currentRoute = currentRoute.toRoute()!!,
                    onItemClick = { route ->
                        route?.let {
                            navigateToTab(navController, it)
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        // Navigation host with padding applied
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Route.HomeScreen.route) {
                HomeScreen(navController = navController)
            }
            composable(Route.AddScreen.route) {
                AddScreen(navController = navController)
            }
            composable(Route.ProfileScreen.route) {
                ProfileScreen(navController = navController)
            }
            // Search Flow (Search Screen)
            composable(Route.SearchScreen.route) {
                SearchScreen(navController = navController)
            }
        }
    }
}

private fun navigateToTab(navController: NavController, route: Route) {
    navController.navigate(route.route) {
        popUpTo(navController.graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewWarranty() {
    WarrantyNavigator(
        navController = rememberNavController(),
        startDestination = Route.HomeScreen.route
    )
}
