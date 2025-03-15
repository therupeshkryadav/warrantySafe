package com.warrantysafe.app.domain.model

data class User(
    val name: String = "",    // User's full name
    val email: String = "",       // User's email address
    val phoneNumber: String = "", // User's phone number
    val password: String = "",    // User's password (hashed in a real application)
    val profileImageUrl: String = "", // URL to the user's profile image
)

