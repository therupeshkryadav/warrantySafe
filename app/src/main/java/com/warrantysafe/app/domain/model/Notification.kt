package com.warrantysafe.app.domain.model

data class Notification(
    val id: Int,
    val notification: String,
    val isRead: Boolean = false
)