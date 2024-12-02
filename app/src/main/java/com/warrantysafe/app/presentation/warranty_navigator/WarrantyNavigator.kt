package com.warrantysafe.app.presentation.warranty_navigator

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.add.AddScreen
import com.warrantysafe.app.presentation.common.CustomTopAppBar
import com.warrantysafe.app.presentation.common.components.actions
import com.warrantysafe.app.presentation.common.components.navigationIcons
import com.warrantysafe.app.presentation.common.components.textCompose
import com.warrantysafe.app.presentation.home.HomeScreen
import com.warrantysafe.app.presentation.navgraph.Route
import com.warrantysafe.app.presentation.profile.ProfileScreen
import com.warrantysafe.app.presentation.search.SearchScreen
import com.warrantysafe.app.presentation.warranty_navigator.components.BottomNavigationItem
import com.warrantysafe.app.presentation.warranty_navigator.components.WarrantyBottomNavigation

@Composable
fun WarrantyNavigator(
    navController: NavHostController,
    startDestination: String
) {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.home_warranty, text = "Home"),
            BottomNavigationItem(icon = R.drawable.add_warranty, text = "Add"),
            BottomNavigationItem(icon = R.drawable.profile_warranty, text = "Profile")
        )
    }

    val backStackState = navController.currentBackStackEntryAsState().value

    // Determine the selected item based on the current back stack
    val selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.AddScreen.route -> 1
        Route.ProfileScreen.route -> 2
        else -> 0
    }

    // Show bottom navigation for specific routes
    val isBottomBarVisible = backStackState?.destination?.route in listOf(
        Route.HomeScreen.route,
        Route.ProfileScreen.route
    )

    // Observe the current route for top bar logic
    val currentRoute = backStackState?.destination?.route


    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = {
                    when (currentRoute) {
                        Route.HomeScreen.route, Route.ProfileScreen.route -> textCompose(
                            isSearch = false,
                            isAddWarranty = false,
                            isHomeorProfile = true
                        )

                        Route.SearchScreen.route->textCompose(
                            isSearch = true,
                            isAddWarranty = false,
                            isHomeorProfile = false
                        )

                        Route.AddScreen.route -> textCompose(
                            isSearch = false,
                            isAddWarranty = true,
                            isHomeorProfile = false
                        )
                        else -> {} // Optional: No title for other screens
                    }
                },
                navigationIcon = {
                    when (currentRoute) {
                        Route.HomeScreen.route, Route.ProfileScreen.route -> navigationIcons(
                            navController = navController,
                            isSearch = false,
                            isAddWarranty = false,
                            isHomeorProfile = true
                        )

                        Route.SearchScreen.route -> navigationIcons(
                            navController = navController,
                            isSearch = true,
                            isAddWarranty = false,
                            isHomeorProfile = false
                        )

                        Route.AddScreen.route -> navigationIcons(
                            navController = navController,
                            isSearch = false,
                            isAddWarranty = true,
                            isHomeorProfile = false
                        )
                        else -> {} // Optional: No navigation icon for other screens
                    }
                },
                actions = {
                    when (currentRoute) {
                        Route.HomeScreen.route, Route.ProfileScreen.route -> actions(
                            navController = navController,
                            isSearch= false,
                            isAddWarranty = false,
                            isHomeorProfile = true
                        )

                        Route.SearchScreen.route -> actions(
                            navController = navController,
                            isSearch= true,
                            isAddWarranty = false,
                            isHomeorProfile = false
                        )

                        Route.AddScreen.route -> actions(
                            navController = navController,
                            isSearch= false,
                            isAddWarranty = true,
                            isHomeorProfile = false
                        )
                        else -> {} // Optional: No actions for other screens
                    }
                }
            )
        },
        bottomBar = {
            if (isBottomBarVisible) {
                WarrantyBottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(navController, Route.HomeScreen.route)
                            1 -> navigateToTab(navController, Route.AddScreen.route)
                            2 -> navigateToTab(navController, Route.ProfileScreen.route)
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

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeRoute ->
            popUpTo(homeRoute) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWarranty(){
    WarrantyNavigator(navController = rememberNavController(),
        startDestination = Route.AddScreen.route)
}
