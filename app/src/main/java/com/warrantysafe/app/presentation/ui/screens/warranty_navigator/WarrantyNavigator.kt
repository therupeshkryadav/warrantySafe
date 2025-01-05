package com.warrantysafe.app.presentation.ui.screens.warranty_navigator

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.ui.screens.aboutApp.AboutAppScreen
import com.warrantysafe.app.presentation.ui.screens.add.AddScreen
import com.warrantysafe.app.presentation.ui.screens.common.customTopAppBar.CustomTopAppBar
import com.warrantysafe.app.presentation.ui.screens.common.productList.ProductList
import com.warrantysafe.app.presentation.ui.screens.common.sideDrawer.SideDrawerContent
import com.warrantysafe.app.presentation.ui.screens.helpSupport.HelpSupportScreen
import com.warrantysafe.app.presentation.ui.screens.home.HomeScreen
import com.warrantysafe.app.presentation.ui.screens.home.components.productDetailsScreen.ProductDetailsScreen
import com.warrantysafe.app.presentation.ui.screens.home.components.productDetailsScreen.edit.EditProductDetailsScreen
import com.warrantysafe.app.presentation.ui.screens.notifification.NotificationScreen
import com.warrantysafe.app.presentation.ui.screens.profile.ProfileScreen
import com.warrantysafe.app.presentation.ui.screens.profile.edit.EditProfileScreen
import com.warrantysafe.app.presentation.ui.screens.search.SearchScreen
import com.warrantysafe.app.presentation.ui.screens.settings.SettingsScreen
import com.warrantysafe.app.presentation.ui.screens.termsPrivacy.TermsPrivacyScreen
import com.warrantysafe.app.presentation.ui.screens.upcomingFeatures.UpcomingFeaturesScreen
import com.warrantysafe.app.presentation.ui.screens.warranty_navigator.components.BottomNavigationItem
import com.warrantysafe.app.presentation.ui.screens.warranty_navigator.components.WarrantyBottomNavigation
import com.warrantysafe.app.presentation.viewModel.NotificationViewModel
import com.warrantysafe.app.presentation.viewModel.ProductViewModel
import com.warrantysafe.app.presentation.viewModel.UserViewModel
import com.warrantysafe.app.utils.toRoute
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

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
            BottomNavigationItem(
                icon = R.drawable.home_warranty,
                text = "Home",
                route = Route.HomeScreen
            ),
            BottomNavigationItem(
                icon = R.drawable.add_warranty,
                text = "Add",
                route = Route.AddScreen
            ),
            BottomNavigationItem(
                icon = R.drawable.profile_warranty,
                text = "Profile",
                route = Route.ProfileScreen
            )
        )
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    // Use Koin to inject ViewModels
    val productViewModel: ProductViewModel = koinViewModel()
    val notificationViewModel: NotificationViewModel = koinViewModel()
    val userViewModel: UserViewModel = koinViewModel()

    // Trigger `loadProducts`,'loadActiveProducts','loadExpiredProducts','loadNotifications','loadUserDetails' when the AppNavGraph is initialized
    LaunchedEffect(Unit) {
        productViewModel.loadProducts()
        productViewModel.loadActiveProducts()
        productViewModel.loadExpiredProducts()
        notificationViewModel.loadNotifications()
        userViewModel.loadUserDetails()
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideDrawerContent(
                onItemClicked = { item ->
                    coroutineScope.launch { drawerState.close() }
                    // Perform navigation or actions based on the clicked item
                    when (item) {
                        "List of Product Cards" -> {
                            navigateToTab(
                                navController = navController,
                                route = Route.ProductList
                            ) // Handle List of Product navigation
                        }

                        "Help & Support" -> {
                            navigateToTab(
                                navController = navController,
                                route = Route.HelpSupportScreen
                            ) // Handle Help & Support navigation
                        }

                        "Rate and Review" -> {
                            // Handle Rate and Review navigation
                        }

                        "Share with Friends" -> {
                            // Handle Share with Friends navigation
                        }

                        "Terms & Privacy" -> {
                            navigateToTab(
                                navController = navController,
                                route = Route.TermsPrivacyScreen
                            )// Handle Terms & Privacy navigation
                        }

                        "About the App" -> {
                            navigateToTab(
                                navController = navController,
                                route = Route.AboutAppScreen
                            )// Handle About the App navigation
                        }

                        "Upcoming Features" -> {
                            navigateToTab(
                                navController = navController,
                                route = Route.UpcomingFeaturesScreen
                            )// Handle Upcoming Features navigation
                        }

                        "Settings" -> {
                            navigateToTab(
                                navController = navController,
                                route = Route.SettingsScreen
                            )//  Handle Settings navigation
                        }
                    }
                }
            )
        },
        gesturesEnabled = currentRoute in listOf(
            Route.HomeScreen.route,
            Route.ProfileScreen.route
        ) // Enable gestures only for specific routes
    ) {
        Scaffold(
            topBar = {
                currentRoute?.let {
                    CustomTopAppBar(
                        navController = navController,
                        currentRoute = it,
                        drawerState = drawerState
                    )
                }
            },
            bottomBar = {
                if (isBottomBarVisible) {
                    WarrantyBottomNavigation(
                        items = bottomNavigationItems,
                        currentRoute = currentRoute.toRoute()!!,
                        onItemClick = { route ->
                            navigateToTab(navController, route)
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
                    HomeScreen(
                        navController = navController,
                        activeProducts = productViewModel.activeProducts.value,
                        expiredProducts = productViewModel.expiredProducts.value
                    )
                }
                composable(Route.AddScreen.route) {
                    AddScreen(navController = navController)
                }

                composable(Route.ProductList.route) {
                    ProductList(
                        navController = navController,
                        productList = productViewModel.products.value
                    )
                }

                composable(Route.HelpSupportScreen.route) {
                    HelpSupportScreen()
                }

                composable(Route.TermsPrivacyScreen.route) {
                    TermsPrivacyScreen()
                }

                composable(Route.AboutAppScreen.route) {
                    AboutAppScreen()
                }

                composable(Route.UpcomingFeaturesScreen.route) {
                    UpcomingFeaturesScreen()
                }

                composable(Route.SettingsScreen.route) {
                    SettingsScreen()
                }

                composable(Route.NotificationScreen.route) {
                    NotificationScreen(
                        notificationList =notificationViewModel.notifications.value
                    )
                }

                composable(
                    route = "productDetailsScreen/{productName}/{purchaseDate}/{category}/{expiryDate}",
                    arguments = listOf(
                        navArgument("productName") { type = NavType.StringType },
                        navArgument("purchaseDate") { type = NavType.StringType },
                        navArgument("expiryDate") { type = NavType.StringType },
                        navArgument("category") { type = NavType.StringType },
                    )
                ) {
                    val productName = it.arguments?.getString("productName") ?: "Unknown"
                    val purchaseDate = it.arguments?.getString("purchaseDate") ?: "N/A"
                    val expiryDate = it.arguments?.getString("expiryDate") ?: "N/A"
                    val category = it.arguments?.getString("category") ?: "N/A"

                    //Navigate to ProductDetailsScreen -->
                    ProductDetailsScreen(
                        navController = navController,
                        productName = productName,
                        purchaseDate = purchaseDate,
                        category = category,
                        expiryDate = expiryDate
                    )
                }

                composable(
                    route = "editProductDetailsScreen/{productName}/{purchaseDate}/{category}/{expiryDate}",
                    arguments = listOf(
                        navArgument("productName") { type = NavType.StringType },
                        navArgument("purchaseDate") { type = NavType.StringType },
                        navArgument("expiryDate") { type = NavType.StringType },
                        navArgument("category") { type = NavType.StringType }
                    )
                ) {
                    val productName = it.arguments?.getString("productName") ?: "Unknown"
                    val purchaseDate = it.arguments?.getString("purchaseDate") ?: "N/A"
                    val expiryDate = it.arguments?.getString("expiryDate") ?: "N/A"
                    val category = it.arguments?.getString("category") ?: "N/A"

                    //Navigate to EditProductDetailsScreen -->
                    EditProductDetailsScreen(
                        navController = navController,
                        productName = productName,
                        purchaseDate = purchaseDate,
                        category=category,
                        expiryDate = expiryDate,
                    )
                }

                composable(
                    route = "editProfileScreen/{fullName}/{userName}/{emailId}/{phone}",
                    arguments = listOf(
                        navArgument("fullName") { type = NavType.StringType },
                        navArgument("userName") { type = NavType.StringType },
                        navArgument("emailId") { type = NavType.StringType },
                        navArgument("phone") { type = NavType.StringType }
                    )
                ) {
                    val fullName = it.arguments?.getString("fullName") ?: "----"
                    val userName = it.arguments?.getString("userName") ?: "----"
                    val emailId = it.arguments?.getString("emailId") ?: "----"
                    val phone = it.arguments?.getString("phone") ?: "----"

                    //Navigate to EditProfileScreen -->
                    EditProfileScreen(
                        navController = navController,
                        fullName = fullName,
                        userName = userName,
                        emailId = emailId,
                        phoneNumber = phone
                    )
                }

                composable(Route.ProfileScreen.route) {
                    ProfileScreen(
                        navController = navController,
                        user = userViewModel.user.value
                    )
                }
                // Search Flow (Search Screen)
                composable(Route.SearchScreen.route) {
                    SearchScreen(navController = navController)
                }
            }
        }
    }
}

private fun navigateToTab(navController: NavController, route: Route) {
    navController.navigate(route.route) {
        popUpTo(navController.graph.startDestinationId) {
            saveState = true // Save state for tabs
        }
        launchSingleTop = true // Avoid multiple instances of the same destination
        restoreState = true // Restore the state if previously saved
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
