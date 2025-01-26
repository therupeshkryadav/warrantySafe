package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.User
import com.warrantysafe.app.domain.repository.UserRepository
import com.warrantysafe.app.domain.utils.Results

class LoginUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(email: String, password: String): Results<User> {
        return try {
            userRepository.loginUser(email, password)
        } catch (e: Exception) {
            Results.Failure(e) // Return failure with the exception
        }
    }
}
