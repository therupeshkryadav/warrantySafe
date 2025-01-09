package com.warrantysafe.app.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.warrantysafe.app.domain.model.Recent
import com.warrantysafe.app.domain.useCases.GetRecentsUseCase
import kotlinx.coroutines.launch

class RecentViewModel(
    private val getRecentsUseCase: GetRecentsUseCase
): ViewModel() {

    // Initialize recent with a default recent
    var recent = mutableStateOf<List<Recent>>(mutableListOf())

    fun loadRecentSearches() {
        viewModelScope.launch {
            val fetchedRecent = getRecentsUseCase()
            recent.value = fetchedRecent
        }
    }
}