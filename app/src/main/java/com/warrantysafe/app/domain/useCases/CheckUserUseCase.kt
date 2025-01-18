package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.User
import com.warrantysafe.app.domain.repository.UserRepository

class CheckUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): Result<User>{
        return userRepository.getCurrentUserDetails()
    }
}
