package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {

    /**
     * Executes the login process.
     *
     * @param username The username or email of the user.
     * @param password The password of the user.
     * @return A Result object containing the user ID if successful, or an error.
     */
    suspend operator fun invoke(username: String, password: String): Result<String> {
        return authRepository.loginWithUsernameAndPassword(username, password)
    }
}
