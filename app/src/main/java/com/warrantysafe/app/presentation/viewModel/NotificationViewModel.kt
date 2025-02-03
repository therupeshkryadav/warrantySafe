package com.warrantysafe.app.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.warrantysafe.app.domain.model.Notification
import com.warrantysafe.app.domain.useCases.AddNotificationUseCase
import com.warrantysafe.app.domain.useCases.GetNotificationsUseCase
import com.warrantysafe.app.domain.useCases.MarkNotificationReadUseCase
import com.warrantysafe.app.domain.utils.Results
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val getNotificationsUseCase: GetNotificationsUseCase,
    private val addNotificationUseCase: AddNotificationUseCase,
    private val markNotificationReadUseCase: MarkNotificationReadUseCase // 🔥 Inject the new use case
) : ViewModel() {

    private val _allNotificationsState = MutableLiveData<Results<List<Notification>>>()
    val allNotificationsState: LiveData<Results<List<Notification>>> get() = _allNotificationsState

    fun loadNotifications() {
        viewModelScope.launch {
            _allNotificationsState.value = Results.Loading
            try {
                val notifications = getNotificationsUseCase()
                _allNotificationsState.value = notifications
            } catch (e: Exception) {
                _allNotificationsState.value = Results.Failure(e)
            }
        }
    }

    fun markAsRead(notificationId: Int) {
        viewModelScope.launch {
            try {
                markNotificationReadUseCase(notificationId)  // 🔥 Update Firebase
                loadNotifications()  // Refresh UI after update
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun markAllAsRead() {
        val notifications = (allNotificationsState.value as Results.Success).data ?: emptyList()
        viewModelScope.launch {
            try {
                notifications.forEach { markNotificationReadUseCase(it.id) } // 🔥 Update all in Firebase
                loadNotifications()  // Refresh UI
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addNotification(notificationText: String) {
        viewModelScope.launch {
            try {
                addNotificationUseCase.execute(notificationText)
                loadNotifications()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
