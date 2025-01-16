package com.warrantysafe.app.domain.useCases

import android.net.Uri
import com.warrantysafe.app.domain.repository.AuthRepository

class SignupUseCase(private val authRepository: AuthRepository) {

    /**
     * Executes the signup process.
     *
     * @param imageUri The URI of the user's profile image.
     * @param username The username of the user.
     * @param email The email of the user.
     * @param phoneNo The phone number of the user.
     * @param password The password for the user's account.
     * @return A Result object containing the user ID if successful, or an error.
     */
    suspend operator fun invoke(
        imageUri: Uri,
        username: String,
        email: String,
        phoneNo: String,
        password: String
    ): Result<String> {
        return authRepository.signupUser(imageUri, username, email, phoneNo, password)
    }
}
