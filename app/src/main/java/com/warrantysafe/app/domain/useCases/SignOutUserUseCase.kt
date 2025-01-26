package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.repository.UserRepository
import com.warrantysafe.app.domain.utils.Results

class SignOutUserUseCase(private val userRepository: UserRepository) {

    suspend operator fun invoke(): Results<Unit> {
        return userRepository.signOutUser()
    }
}
