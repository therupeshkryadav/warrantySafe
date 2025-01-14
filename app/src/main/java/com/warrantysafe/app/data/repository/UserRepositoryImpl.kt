package com.warrantysafe.app.data.repository

import android.net.Uri
import com.warrantysafe.app.R
import com.warrantysafe.app.domain.model.User
import com.warrantysafe.app.domain.repository.UserRepository

class UserRepositoryImpl : UserRepository {
    private var user = User(
        fullName = "Rupesh Kumar Yadav",
        userName = "therupeshkryadav",
        emailId = "rupesh.official484@gmail.com",
        phone = "7233966649",
        profileImageUri = Uri.parse("android.resource://com.warrantysafe.app/${R.drawable.profile_placeholder}")
    )
    // private val user = User()

    override suspend fun getUserDetail(): User {
        return user
    }

    override suspend fun updateUser(user: User) {
        this.user = user
    }
}