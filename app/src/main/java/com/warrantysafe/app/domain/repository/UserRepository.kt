package com.warrantysafe.app.domain.repository

import com.warrantysafe.app.domain.model.User

interface UserRepository {
    suspend fun checkUser() : String
    suspend fun signUpUser(user: User): Result<User>
    suspend fun loginUser(email: String, password: String): Result<User>
    suspend fun getUser(): Result<User> // Complete the getUser method
    suspend fun updateUser(user: User): Result<User>
    suspend fun signOutUser(): Result<Unit>
}