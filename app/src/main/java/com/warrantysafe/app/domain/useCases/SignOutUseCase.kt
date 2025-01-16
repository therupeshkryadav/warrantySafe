package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.repository.AuthRepository

class SignOutUseCase(private val authRepository: AuthRepository) {

    /**
     * Executes the sign-out process.
     *
     * @return A Result object indicating success or failure.
     */
    suspend operator fun invoke(): Result<Unit> {
        return authRepository.signOut()
    }
}
