package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.User
import com.warrantysafe.app.domain.repository.UserRepository
import com.warrantysafe.app.domain.utils.Results

class GetUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Results<User> {
        return userRepository.getUser()
    }
}


