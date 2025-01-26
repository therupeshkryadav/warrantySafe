package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.User
import com.warrantysafe.app.domain.repository.UserRepository
import com.warrantysafe.app.domain.utils.Results

class UpdateUserUseCase(private val userRepository: UserRepository) {

    suspend operator fun invoke(user: User): Results<User> {
        return try {
            userRepository.updateUser(user)
        } catch (e: Exception) {
            Results.Failure(e)
        }
    }
}
