package com.warrantysafe.app.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.ui.screens.auth.LoginScreen
import com.warrantysafe.app.presentation.ui.screens.auth.SignUpScreen
import com.warrantysafe.app.presentation.ui.screens.main.addScreen.AddScreen
import com.warrantysafe.app.presentation.ui.screens.main.homeScreen.HomeScreen
import com.warrantysafe.app.presentation.ui.screens.main.profileScreen.ProfileScreen
import com.warrantysafe.app.presentation.ui.screens.main.profileScreen.editProfileScreen.EditProfileScreen
import com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.aboutApp.AboutAppScreen
import com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.helpSupportScreen.HelpSupportScreen
import com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.productCardList.ProductCardList
import com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.settingsScreen.SettingsScreen
import com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.termsPrivacyScreen.TermsPrivacyScreen
import com.warrantysafe.app.presentation.ui.screens.main.sideNavDrawer.upcomingFeaturesScreen.UpcomingFeaturesScreen
import com.warrantysafe.app.presentation.ui.screens.main.utils.notificationScreen.NotificationScreen
import com.warrantysafe.app.presentation.ui.screens.main.utils.productDetailScreen.ProductDetailScreen
import com.warrantysafe.app.presentation.ui.screens.main.utils.productDetailScreen.editProductDetailScreen.EditProductDetailScreen
import com.warrantysafe.app.presentation.ui.screens.main.utils.searchScreen.SearchScreen
import com.warrantysafe.app.presentation.ui.screens.splash.SplashScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.SplashScreen.route
    ) {
        // SplashScreen
        composable(Route.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        // LoginScreen
        composable(Route.LoginScreen.route) {
            LoginScreen(navController = navController)
        }

        // SignUpScreen
        composable(Route.SignUpScreen.route) {
            SignUpScreen(navController = navController)
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
            route = "productDetailsScreen/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.StringType }
            )
        ) {
            val id = it.arguments?.getString("id") ?: "Unknown"
            ProductDetailScreen(
                navController = navController,
                productId = id
            )
        }


        // EditProductDetailScreen with arguments
        composable(
            route = "editProductDetailsScreen/{id}/{productName}/{purchaseDate}/{category}/{expiryDate}/{notes}/{imageUri}",
            arguments = listOf(
                navArgument("id") { type = NavType.StringType },
                navArgument("productName") { type = NavType.StringType },
                navArgument("purchaseDate") { type = NavType.StringType },
                navArgument("expiryDate") { type = NavType.StringType },
                navArgument("category") { type = NavType.StringType },
                navArgument("notes") { type = NavType.StringType },
                navArgument("imageUri") { type = NavType.StringType } // Correct NavType for imageUri
            )
        ) {
            val id = it.arguments?.getString("id") ?: "Unknown"
            val productName = it.arguments?.getString("productName") ?: "Unknown"
            val purchaseDate = it.arguments?.getString("purchaseDate") ?: "N/A"
            val expiryDate = it.arguments?.getString("expiryDate") ?: "N/A"
            val category = it.arguments?.getString("category") ?: "N/A"
            val notes = it.arguments?.getString("notes") ?: "N/A"
            // Handle the default image URI logic
            val imageUriString = it.arguments?.getString("imageUri")
            val imageUri = if (imageUriString != null) {
                Uri.parse(imageUriString)
            } else {
                // Default image (placeholder) is provided as a resource ID
                // To use it with Image composable, you need to provide a painter or drawable resource.
                // For example, this is just a placeholder for the case when imageUri is null
                Uri.parse("android.resource://com.example.yourapp/${R.drawable.product_placeholder}")
            }

            EditProductDetailScreen(
                navController = navController,
                productId = id,
                productName = productName,
                purchaseDate = purchaseDate,
                category = category,
                expiryDate = expiryDate,
                notes = notes,
                imageUri = imageUri
            )
        }

        // EditProfileScreen with arguments
        composable(
            route = "editProfileScreen/{profileImgUri}/{fullName}/{userName}/{emailId}/{phone}",
            arguments = listOf(
                navArgument("profileImgUri") { type = NavType.StringType },
                navArgument("fullName") { type = NavType.StringType },
                navArgument("userName") { type = NavType.StringType },
                navArgument("emailId") { type = NavType.StringType },
                navArgument("phone") { type = NavType.StringType }
            )
        ) {
            // Handle the default image URI logic
            val profileImgUriString = it.arguments?.getString("profileImgUri")
            val profileImgUri = if (profileImgUriString != null) {
                Uri.parse(profileImgUriString)
            } else {
                // Default image (placeholder) is provided as a resource ID
                // To use it with Image composable, you need to provide a painter or drawable resource.
                // For example, this is just a placeholder for the case when imageUri is null
                Uri.parse("android.resource://com.warrantysafe.app/${R.drawable.profile_placeholder}")
            }
            val fullName = it.arguments?.getString("fullName") ?: "----"
            val userName = it.arguments?.getString("userName") ?: "----"
            val emailId = it.arguments?.getString("emailId") ?: "----"
            val phone = it.arguments?.getString("phone") ?: "----"

            EditProfileScreen(
                navController = navController,
                profileImgUri = profileImgUri,
                name = fullName,
                username = userName,
                email = emailId,
                phoneNumber = phone
            )
        }

        composable(Route.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }

        composable(Route.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
    }
}
