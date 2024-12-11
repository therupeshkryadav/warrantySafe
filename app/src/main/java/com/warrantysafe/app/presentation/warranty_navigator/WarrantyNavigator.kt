package com.warrantysafe.app.presentation.warranty_navigator

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
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
import com.warrantysafe.app.presentation.add.AddScreen
import com.warrantysafe.app.presentation.common.customTopAppBar.CustomTopAppBar
import com.warrantysafe.app.presentation.common.productList.ProductList
import com.warrantysafe.app.presentation.common.sideDrawer.SideDrawerContent
import com.warrantysafe.app.presentation.home.HomeScreen
import com.warrantysafe.app.presentation.home.Product
import com.warrantysafe.app.presentation.home.components.productDetailsScreen.ProductDetailsScreen
import com.warrantysafe.app.presentation.home.components.productDetailsScreen.edit.EditProductDetailsScreen
import com.warrantysafe.app.presentation.navgraph.Route
import com.warrantysafe.app.presentation.notifification.NotificationScreen
import com.warrantysafe.app.presentation.profile.ProfileScreen
import com.warrantysafe.app.presentation.profile.edit.EditProfileScreen
import com.warrantysafe.app.presentation.search.SearchScreen
import com.warrantysafe.app.presentation.warranty_navigator.components.BottomNavigationItem
import com.warrantysafe.app.presentation.warranty_navigator.components.WarrantyBottomNavigation
import com.warrantysafe.app.utils.toRoute
import kotlinx.coroutines.launch

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

    val productList = listOf(
        Product.Active(
            title = "Realme 3 Pro",
            purchase = "30/11/2024",
            expiry = "",
            period = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Realme 7 Pro",
            purchase = "30/11/2024",
            expiry = "",
            period = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Redmi Note 10 ",
            purchase = "30/11/2024",
            expiry = "",
            period = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Realme 7 Pro",
            purchase = "30/11/2024",
            expiry = "",
            period = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Redmi Note 10 ",
            purchase = "30/11/2024",
            expiry = "",
            period = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Realme 7 Pro",
            purchase = "30/11/2024",
            expiry = "",
            period = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Redmi Note 10 ",
            purchase = "30/11/2024",
            expiry = "",
            period = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Realme 7 Pro",
            purchase = "30/11/2024",
            expiry = "",
            period = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Redmi Note 10 ",
            purchase = "30/11/2024",
            expiry = "",
            period = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Realme 7 Pro",
            purchase = "30/11/2024",
            expiry = "",
            period = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Active(
            title = "Redmi Note 10 ",
            purchase = "30/11/2024",
            expiry = "",
            period = "1 year 0 months 0 days",
            progress = 0.7f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "Rado Watch",
            purchase = "30/11/2024",
            expiry = "",
            period = "0 year 0 months 0 days",
            progress = 1f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "PS5",
            purchase = "30/11/2024",
            expiry = "",
            period = "0 year 0 months 0 days",
            progress = 1f,
            imageResId = R.drawable.item_image_placeholder
        ),
        Product.Expired(
            title = "LG Washing Machine ",
            purchase = "30/11/2024",
            expiry = "",
            period = "0 year 0 months 0 days",
            progress = 1f,
            imageResId = R.drawable.item_image_placeholder
        )
    )

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
                            // Handle Help & Support navigation
                        }

                        "Rate and Review" -> {
                            // Handle Rate and Review navigation
                        }

                        "Share with Friends" -> {
                            // Handle Share with Friends navigation
                        }

                        "Terms & Privacy" -> {
                            // Handle Terms & Privacy navigation
                        }

                        "About the App" -> {
                            // Handle About the App navigation
                        }

                        "Upcoming Features" -> {
                            // Handle Upcoming Features navigation
                        }

                        "Settings" -> {
                            //  Handle Settings navigation
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

                composable(Route.ProductList.route){
                    ProductList(
                        navController = navController,
                        productType = productList
                    )
                }
                composable(Route.NotificationScreen.route) {
                    NotificationScreen(navController = navController)
                }
                composable(
                    route = "productDetailsScreen/{productName}/{purchaseDate}/{expiryDate}/{progress}/{period}",
                    arguments = listOf(
                        navArgument("productName") { type = NavType.StringType },
                        navArgument("purchaseDate") { type = NavType.StringType },
                        navArgument("expiryDate") { type = NavType.StringType },
                        navArgument("progress") { type = NavType.FloatType },
                        navArgument("period") { type = NavType.StringType }
                    )
                ) {
                    val productName = it.arguments?.getString("productName") ?: "Unknown"
                    val purchaseDate = it.arguments?.getString("purchaseDate") ?: "N/A"
                    val expiryDate = it.arguments?.getString("expiryDate") ?: "N/A"
                    val progress = it.arguments?.getFloat("progress") ?: 0f
                    val period = it.arguments?.getString("period") ?: "N/A"

                    //Navigate to ProductDetailsScreen -->
                    ProductDetailsScreen(
                        navController = navController,
                        productName = productName,
                        purchaseDate = purchaseDate,
                        expiryDate = expiryDate,
                        progress = progress,
                        period = period
                    )
                }

                composable(
                    route = "editProductDetailsScreen/{productName}/{purchaseDate}/{expiryDate}",
                    arguments = listOf(
                        navArgument("productName") { type = NavType.StringType },
                        navArgument("purchaseDate") { type = NavType.StringType },
                        navArgument("expiryDate") { type = NavType.StringType }
                    )
                ) {
                    val productName = it.arguments?.getString("productName") ?: "Unknown"
                    val purchaseDate = it.arguments?.getString("purchaseDate") ?: "N/A"
                    val expiryDate = it.arguments?.getString("expiryDate") ?: "N/A"

                    //Navigate to EditProductDetailsScreen -->
                    EditProductDetailsScreen(
                        navController = navController,
                        productName = productName,
                        purchaseDate = purchaseDate,
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
                    ProfileScreen(navController = navController)
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
