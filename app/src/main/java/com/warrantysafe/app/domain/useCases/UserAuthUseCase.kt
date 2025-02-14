package com.warrantysafe.app.domain.useCases

import com.warrantysafe.app.domain.model.User
import com.warrantysafe.app.domain.repository.UserRepository
import com.warrantysafe.app.domain.utils.Results

class CheckUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): String {
        return userRepository.checkUser()
    }
}

class GetUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Results<User> {
        return userRepository.getUser()
    }
}

class SignUpUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User): Results<User> {
        return try {
            userRepository.signUpUser(user)
        }catch (e: Exception) {
            Results.Failure(e) // Return failure with the exception
        }
    }
}

class LoginUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): Results<User> {
        return try {
            userRepository.loginUser(email, password)
        } catch (e: Exception) {
            Results.Failure(e) // Return failure with the exception
        }
    }
}

class UpdateUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User): Results<User> {
        return try {
            userRepository.updateUser(user)
        } catch (e: Exception) {
            Results.Failure(e)
        }
    }
}

class SignOutUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Results<Unit> {
        return userRepository.signOutUser()
    }
}