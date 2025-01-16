package com.warrantysafe.app.domain.repository

import android.net.Uri

interface AuthRepository {
    /**
     * Log in a user using their username or email and password.
     *
     * @param username The username or email of the user.
     * @param password The password of the user.
     * @return A Result object containing the user ID if successful, or an error.
     */
    suspend fun loginWithUsernameAndPassword(username: String, password: String): Result<String>

    /**
     * Sign up a new user.
     *
     * @param imageUri The URI of the user's profile image.
     * @param username The username of the user.
     * @param email The email of the user.
     * @param phoneNo The phone number of the user.
     * @param password The password for the user's account.
     * @return A Result object containing the user ID if successful, or an error.
     */
    suspend fun signupUser(
        imageUri: Uri,
        username: String,
        email: String,
        phoneNo: String,
        password: String
    ): Result<String>

    /**
     * Sign out the currently logged-in user.
     *
     * @return A Result object indicating success or failure.
     */
    suspend fun signOut(): Result<Unit>
}


