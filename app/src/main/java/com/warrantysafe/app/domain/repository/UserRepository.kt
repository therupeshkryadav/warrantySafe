package com.warrantysafe.app.domain.repository

import com.warrantysafe.app.domain.model.User
import com.warrantysafe.app.domain.utils.Results

interface UserRepository {
    suspend fun checkUser() : String
    suspend fun signUpUser(user: User): Results<User>
    suspend fun sendPasswordResetLink(email: String): Results<Unit>
    suspend fun loginUser(email: String, password: String): Results<User>
    suspend fun getUser(): Results<User>
    suspend fun updateUser(user: User): Results<User>
    suspend fun changePassword(currentPassword: String, newPassword: String): Results<Unit>
    suspend fun deleteUser(password: String): Results<Unit>
    suspend fun signOutUser(): Results<Unit>
}