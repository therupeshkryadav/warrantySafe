package com.warrantysafe.app.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.warrantysafe.app.domain.model.Upcoming
import com.warrantysafe.app.domain.repository.UpcomingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UpcomingViewModel(private val repository: UpcomingRepository) : ViewModel() {

    private val _upcomingNotifications = MutableStateFlow<List<Upcoming>>(emptyList())
    val upcomingNotifications: StateFlow<List<Upcoming>> = _upcomingNotifications

    fun fetchUpcomingNotifications() {
        viewModelScope.launch {
            val notifications = repository.getUpcomingNotifications()
            _upcomingNotifications.value = notifications
        }
    }
}
