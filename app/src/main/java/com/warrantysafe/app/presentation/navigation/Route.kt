package com.warrantysafe.app.presentation.navigation

import android.net.Uri
import androidx.compose.ui.graphics.painter.Painter

sealed class Route(
    val route : String
){
    object SplashScreen : Route(route = "splashScreen")
    object LoginScreen : Route(route = "loginScreen")
    object SignUpScreen : Route(route = "signUpScreen")
    object HomeScreen : Route(route = "homeScreen")
    object SearchScreen : Route(route = "search_screen")
    object AddScreen : Route(route = "addScreen")
    object ProfileScreen : Route(route = "profileScreen")
    object ProductCardList : Route(route = "productCardList")
    object HelpSupportScreen : Route(route = "helpSupportScreen")
    object TermsPrivacyScreen : Route(route = "termsPrivacyScreen")
    object AboutAppScreen : Route(route = "aboutAppScreen")
    object UpcomingFeaturesScreen : Route(route = "upcomingFeaturesScreen")
    object SettingsScreen : Route(route = "settingsScreen")

    object ProductDetailsScreen : Route("productDetailsScreen/{productId}/{productName}/{purchaseDate}/{category}/{expiryDate}/{notes}/{imageUri}") {
        fun createRoute(
            productId: String?,
            productName: String?,
            purchaseDate: String?,
            category: String?,
            expiryDate: String?,
            notes: String?,
            imageUri: Uri?
        ): String {
            // Provide safe defaults and encode the values
            val safeProductName = Uri.encode(productName ?: "Unknown")
            val safeProductId = Uri.encode(productId ?: "Unknown")
            val safePurchaseDate = Uri.encode(purchaseDate ?: "N/A")
            val safeExpiryDate = Uri.encode(expiryDate ?: "N/A")
            val safeCategory = Uri.encode(category ?: "N/A")
            val safeNotes = Uri.encode(notes ?: "N/A")
            val safeImageUri = Uri.encode(imageUri?.toString() ?: "N/A")

            return "productDetailsScreen/$safeProductId/$safeProductName/$safePurchaseDate/$safeCategory/$safeExpiryDate/$safeNotes/$safeImageUri"
        }
    }

    object EditProductDetailsScreen : Route("editProductDetailsScreen/{productId}/{productName}/{purchaseDate}/{category}/{expiryDate}/{notes}/{imageUri}") {
        fun createRoute(
            productId: String?,
            productName: String?,
            purchaseDate: String?,
            expiryDate: String?,
            category: String?,
            notes: String?,
            imageUri: Uri?
        ): String {
            // Provide safe defaults and encode the values
            val safeProductId = Uri.encode(productId)?: "Unknown"
            val safeProductName = Uri.encode(productName ?: "Unknown")
            val safePurchaseDate = Uri.encode(purchaseDate ?: "N/A")
            val safeExpiryDate = Uri.encode(expiryDate ?: "N/A")
            val safeCategory = Uri.encode(category ?: "N/A")
            val safeNotes = Uri.encode(notes ?: "N/A")
            val safeImageUri = Uri.encode(imageUri?.toString() ?: "N/A")

            return "editProductDetailsScreen/$safeProductId/$safeProductName/$safePurchaseDate/$safeCategory/$safeExpiryDate/$safeNotes/$safeImageUri"
        }
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

    object NotificationScreen : Route(route = "notificationScreen")

}