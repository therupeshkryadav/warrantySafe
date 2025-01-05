package com.warrantysafe.app.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.warrantysafe.app.domain.model.User
import com.warrantysafe.app.domain.useCases.GetUserDetailUseCase
import kotlinx.coroutines.launch

class UserViewModel(
    private val getUserDetailUseCase: GetUserDetailUseCase
): ViewModel() {

    // Initialize with a default user
    var user = mutableStateOf(User(fullName = "", userName = "", emailId = "", phone = ""))

    fun loadUserDetails() {
        viewModelScope.launch {
            val fetchedUser = getUserDetailUseCase()
            user.value = fetchedUser
        }
    }

}