package com.warrantysafe.app.presentation.navigation

import android.net.Uri

sealed class Route(
    val route: String
) {
    object SplashScreen : Route(route = "splashScreen")
    object LoginScreen : Route(route = "loginScreen")
    object SignUpScreen : Route(route = "signUpScreen")
    object HomeScreen : Route(route = "homeScreen")
    object SearchScreen : Route(route = "search_screen")
    object AddScreen : Route(route = "addScreen")
    object ProfileScreen : Route(route = "profileScreen")
    object ProductCardList : Route(route = "productCardList")
    object HelpSupportScreen : Route(route = "helpSupportScreen")
    object TermsConditionScreen : Route(route = "termsConditionScreen")
    object PrivacyPolicyScreen : Route(route = "privacyPolicyScreen")
    object AboutAppScreen : Route(route = "aboutAppScreen")
    object UpcomingFeaturesScreen : Route(route = "upcomingFeaturesScreen")
    object SettingsScreen : Route(route = "settingsScreen")

    object ProductDetailsScreen : Route("productDetailsScreen/{id}") {
        fun createRoute(
            id: String?
        ): String {
            // Provide safe defaults and encode the values
            val safeProductId = Uri.encode(id ?: "Unknown")

            return "productDetailsScreen/$safeProductId"
        }
    }


    object EditProductDetailsScreen : Route("editProductDetailsScreen/{productJson}")

    object EditProfileScreen :
        Route("editProfileScreen/{profileImgUrl}/{fullName}/{userName}/{emailId}/{phone}") {
        fun createRoute(
            profileImgUri: Uri?,
            fullName: String?,
            userName: String?,
            emailId: String?,
            phone: String?
        ): String {
            // Provide safe defaults and encode the values
            val safeFullName = Uri.encode(fullName ?: "----")
            val safeProfileImgUri = Uri.encode(profileImgUri?.toString() ?: "N/A")
            val safeUserName = Uri.encode(userName ?: "----")
            val safeEmailId = Uri.encode(emailId ?: "----")
            val safePhone = Uri.encode(phone ?: "----")
            return "editProfileScreen/$safeProfileImgUri/$safeFullName/$safeUserName/$safeEmailId/$safePhone"
        }
    }

    object NotificationScreen : Route(route = "notificationScreen")

}