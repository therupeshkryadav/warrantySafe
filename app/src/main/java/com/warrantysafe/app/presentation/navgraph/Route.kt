package com.warrantysafe.app.presentation.navgraph

import android.net.Uri

sealed class Route(
    val route : String
){
    object SplashSheet : Route(route = "splashSheet")
    object LoginSignUpScreen : Route(route = "loginSignUpScreen")
    object HomeScreen : Route(route = "homeScreen")
    object SearchScreen : Route(route = "search_screen")
    object AddScreen : Route(route = "addScreen")
    object ProfileScreen : Route(route = "profileScreen")
    object ProductList : Route(route = "productList")
    object HelpSupportScreen : Route(route = "helpSupportScreen")
    object TermsPrivacyScreen : Route(route = "termsPrivacyScreen")
    object AboutAppScreen : Route(route = "aboutAppScreen")
    object UpcomingFeaturesScreen : Route(route = "upcomingFeaturesScreen")
    object SettingsScreen : Route(route = "settingsScreen")

    object ProductDetailsScreen : Route("productDetailsScreen/{productName}/{purchaseDate}/{expiryDate}") {
        fun createRoute(
            productName: String?,
            purchaseDate: String?,
            expiryDate: String?
        ) :String{
            // Provide safe defaults and encode the values
            val safeProductName = Uri.encode(productName ?: "Unknown")
            val safePurchaseDate = Uri.encode(purchaseDate ?: "N/A")
            val safeExpiryDate = Uri.encode(expiryDate ?: "N/A")
            return "productDetailsScreen/$safeProductName/$safePurchaseDate/$safeExpiryDate"}
    }

    object EditProfileScreen : Route("editProfileScreen/{fullName}/{userName}/{emailId}/{phone}") {
        fun createRoute(
            fullName: String?,
            userName: String?,
            emailId: String?,
            phone: String?
        ) :String{
            // Provide safe defaults and encode the values
            val safeFullName = Uri.encode(fullName ?: "----")
            val safeUserName = Uri.encode(userName ?: "----")
            val safeEmailId = Uri.encode(emailId ?: "----")
            val safePhone = Uri.encode(phone ?: "----")
            return "editProfileScreen/$safeFullName/$safeUserName/$safeEmailId/$safePhone"}
    }

    object EditProductDetailsScreen : Route("editProductDetailsScreen/{productName}/{purchaseDate}/{expiryDate}") {
        fun createRoute(
            productName: String?,
            purchaseDate: String?,
            expiryDate: String?
        ) :String{
            // Provide safe defaults and encode the values
            val safeProductName = Uri.encode(productName ?: "Unknown")
            val safePurchaseDate = Uri.encode(purchaseDate ?: "N/A")
            val safeExpiryDate = Uri.encode(expiryDate ?: "N/A")
            return "editProductDetailsScreen/$safeProductName/$safePurchaseDate/$safeExpiryDate"}
    }

    object NotificationScreen : Route(route = "notificationScreen")
    object BottomNavigation : Route(route = "bottomNavigation")
}