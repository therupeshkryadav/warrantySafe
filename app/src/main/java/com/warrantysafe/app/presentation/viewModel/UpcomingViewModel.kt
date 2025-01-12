package com.warrantysafe.app.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.warrantysafe.app.domain.model.Upcoming
import com.warrantysafe.app.domain.useCases.GetUpcomingUseCase
import kotlinx.coroutines.launch

class UpcomingViewModel(
    private val getUpcomingUseCase: GetUpcomingUseCase
):ViewModel() {
    // State holders for all, active, and expired products
    var upcomingNotifications = mutableStateOf<List<Upcoming>>(mutableListOf())

    // Load all products
    fun loadUpcoming() {
        viewModelScope.launch {
            val fetchedUpcoming = getUpcomingUseCase()
            upcomingNotifications.value = fetchedUpcoming
        }
    }
}