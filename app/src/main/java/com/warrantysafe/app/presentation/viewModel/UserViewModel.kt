package com.warrantysafe.app.presentation.viewModel

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.warrantysafe.app.domain.model.User
import com.warrantysafe.app.domain.useCases.GetUserDetailUseCase
import com.warrantysafe.app.domain.useCases.UpdateUserUseCase
import kotlinx.coroutines.launch

class UserViewModel(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val updateUserUseCase: UpdateUserUseCase
): ViewModel() {

    // Initialize with a default user
    var user = mutableStateOf(User(fullName = "", userName = "", emailId = "", phone = "", profileImageUri = Uri.EMPTY))

    fun loadUserDetails() {
        viewModelScope.launch {
            val fetchedUser = getUserDetailUseCase()
            user.value = fetchedUser
        }
    }

    fun updateUserDetails(user: User) {
        viewModelScope.launch {
            updateUserUseCase(user)
        }
    }

}