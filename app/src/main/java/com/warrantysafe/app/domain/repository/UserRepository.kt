package com.warrantysafe.app.domain.repository

import com.warrantysafe.app.domain.model.User

interface UserRepository {
    suspend fun getUserDetail(): User
}