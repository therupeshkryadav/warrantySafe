package com.warrantysafe.app.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.warrantysafe.app.domain.model.BottomNavigation
import com.warrantysafe.app.domain.useCases.GetBottomNavigationIconsUseCase
import kotlinx.coroutines.launch

class BottomNavigationViewModel(
    private val getBottomNavigationIconsUseCase: GetBottomNavigationIconsUseCase
):ViewModel() {
    var bottomNavigation = mutableStateOf<List<BottomNavigation>>(mutableListOf())

    // Load all products
    fun loadBottomNavigationIcons() {
        viewModelScope.launch {
            val fetchedBottomNavigationIcons = getBottomNavigationIconsUseCase()
            bottomNavigation.value = fetchedBottomNavigationIcons
        }
    }
}