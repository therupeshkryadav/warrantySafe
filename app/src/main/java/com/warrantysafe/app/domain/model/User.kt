package com.warrantysafe.app.domain.model

data class User(
    val name: String = "",    // User's full name
    val username: String = "",    // Username chosen by the user but unique
    val email: String = "",       // User's email address
    val phoneNumber: String = "", // User's phone number
    val password: String = "",    // User's password (hashed in a real application)
    val profileImageUri: String = "", // URL to the user's profile image
)

