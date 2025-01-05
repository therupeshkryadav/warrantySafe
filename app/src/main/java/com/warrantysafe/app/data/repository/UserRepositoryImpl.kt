package com.warrantysafe.app.data.repository

import com.warrantysafe.app.domain.model.User
import com.warrantysafe.app.domain.repository.UserRepository

class UserRepositoryImpl : UserRepository {
    private val user = User(
        fullName = "Rupesh Kumar Yadav",
        userName = "therupeshkryadav",
        emailId = "rupesh.official484@gmail.com",
        phone = "7233966649"
    )
    override suspend fun getUserDetail(): User {
        return user
    }
}