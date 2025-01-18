package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.repository.UserRepository

class SignOutUserUseCase(private val userRepository: UserRepository) {

    suspend operator fun invoke(): Result<Unit> {
        return userRepository.signOutUser()
    }
}
