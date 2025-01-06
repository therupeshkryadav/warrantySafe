package com.warrantysafe.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.warrantysafe.app.presentation.ui.screens.aboutApp.AboutAppScreen
import com.warrantysafe.app.presentation.ui.screens.addScreen.AddScreen
import com.warrantysafe.app.presentation.ui.screens.helpSupportScreen.HelpSupportScreen
import com.warrantysafe.app.presentation.ui.screens.homeScreen.HomeScreen
import com.warrantysafe.app.presentation.ui.screens.homeScreen.components.productDetailScreen.ProductDetailScreen
import com.warrantysafe.app.presentation.ui.screens.homeScreen.components.productDetailScreen.editProductDetailScreen.EditProductDetailScreen
import com.warrantysafe.app.presentation.ui.screens.loginSignUpScreen.LoginSignUpScreen
import com.warrantysafe.app.presentation.ui.screens.notifificationScreen.NotificationScreen
import com.warrantysafe.app.presentation.ui.screens.profileScreen.ProfileScreen
import com.warrantysafe.app.presentation.ui.screens.profileScreen.editProfileScreen.EditProfileScreen
import com.warrantysafe.app.presentation.ui.screens.searchScreen.SearchScreen
import com.warrantysafe.app.presentation.ui.screens.settingsScreen.SettingsScreen
import com.warrantysafe.app.presentation.ui.screens.splashScreen.SplashScreen
import com.warrantysafe.app.presentation.ui.screens.termsPrivacyScreen.TermsPrivacyScreen
import com.warrantysafe.app.presentation.ui.screens.upcomingFeaturesScreen.UpcomingFeaturesScreen
import com.warrantysafe.app.presentation.ui.screens.utils.productCardList.ProductCardList

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.SplashScreen.route
    ) {
        // SplashScreen
        composable(Route.SplashScreen.route) {
            SplashScreen(
                onFinish = {
                    navController.navigate(Route.LoginSignUpScreen.route) {
                        popUpTo(Route.SplashScreen.route) { inclusive = true }
                    }
                }
            )
        }

        // LoginSignUpScreen
        composable(Route.LoginSignUpScreen.route) {
            LoginSignUpScreen(
                onLoginSuccess = {
                    navController.navigate(Route.HomeScreen.route) {
                        popUpTo(Route.LoginSignUpScreen.route) { inclusive = true }
                    }
                },
                onSignUpSuccess = {
                    navController.navigate(Route.LoginSignUpScreen.route)
                }
            )
        }

        // HomeScreen
        composable(Route.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        // Other screens
        composable(Route.AddScreen.route) {
            AddScreen(navController = navController)
        }

        composable(Route.ProductCardList.route) {
            ProductCardList(navController = navController)
        }

        composable(Route.HelpSupportScreen.route) {
            HelpSupportScreen(navController = navController)
        }

        composable(Route.TermsPrivacyScreen.route) {
            TermsPrivacyScreen(navController = navController)
        }

        composable(Route.AboutAppScreen.route) {
            AboutAppScreen(navController = navController)
        }

        composable(Route.UpcomingFeaturesScreen.route) {
            UpcomingFeaturesScreen(navController = navController)
        }

        composable(Route.SettingsScreen.route) {
            SettingsScreen(navController = navController)
        }

        composable(Route.NotificationScreen.route) {
            NotificationScreen(navController = navController)
        }

        // ProductDetailScreen with arguments
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

            ProductDetailScreen(
                navController = navController,
                productName = productName,
                purchaseDate = purchaseDate,
                category = category,
                expiryDate = expiryDate
            )
        }

        // EditProductDetailScreen with arguments
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

            EditProductDetailScreen(
                navController = navController,
                productName = productName,
                purchaseDate = purchaseDate,
                category = category,
                expiryDate = expiryDate,
            )
        }

        // EditProfileScreen with arguments
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

        composable(Route.SearchScreen.route) {
            SearchScreen(
                navController = navController,
                recentSearches = listOf("recent1", "recent2", "recent3", "recent4")
            )
        }
    }
}
