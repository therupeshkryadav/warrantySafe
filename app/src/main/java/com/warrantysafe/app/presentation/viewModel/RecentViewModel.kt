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

    fun addToRecent(query: String) {
        if (query.isNotEmpty()) {
            val updatedList = recent.value.toMutableList()
            // Check if the query already exists, remove it to avoid duplicates
            updatedList.removeAll { it.recent == query }
            // Add the new query at the top
            updatedList.add(0, Recent(recent = query))
            // Keep only the last 4 items
            recent.value = updatedList.take(4)
        }
    }
}