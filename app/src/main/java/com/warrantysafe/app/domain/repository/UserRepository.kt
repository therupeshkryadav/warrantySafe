package com.warrantysafe.app.domain.repository

import com.warrantysafe.app.domain.model.User

interface UserRepository {
    suspend fun signUpUser(user: User): Result<User>
    suspend fun loginUser(email: String, password: String): Result<User>
    suspend fun updateUser(user: User): Result<Unit>
    suspend fun signOutUser(): Result<Unit>
}