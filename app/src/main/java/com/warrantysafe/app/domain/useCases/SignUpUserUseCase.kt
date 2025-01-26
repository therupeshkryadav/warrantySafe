package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.User
import com.warrantysafe.app.domain.repository.UserRepository
import com.warrantysafe.app.domain.utils.Results

class SignUpUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User): Results<User> {
        return try {
            userRepository.signUpUser(user)
        }catch (e: Exception) {
            Results.Failure(e) // Return failure with the exception
        }
    }
}
