package com.warrantysafe.app.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.warrantysafe.app.domain.model.Notification
import com.warrantysafe.app.domain.useCases.GetNotificationsUseCase
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val getNotificationsUseCase: GetNotificationsUseCase
): ViewModel() {
    var notifications = mutableStateOf<List<Notification>>(mutableListOf())

    // Load all products
    fun loadNotifications() {
        viewModelScope.launch {
            val fetchedNotifications = getNotificationsUseCase()
            notifications.value = fetchedNotifications
        }
    }
}