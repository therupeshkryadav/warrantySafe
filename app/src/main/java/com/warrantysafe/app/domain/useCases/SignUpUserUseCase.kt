package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.User
import com.warrantysafe.app.domain.repository.UserRepository

class SignUpUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User): Result<User>{
        return userRepository.signUpUser(user)
    }
}
