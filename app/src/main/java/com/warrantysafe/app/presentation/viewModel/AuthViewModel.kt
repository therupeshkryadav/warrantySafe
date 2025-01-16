package com.warrantysafe.app.presentation.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.warrantysafe.app.domain.useCases.LoginUseCase
import com.warrantysafe.app.domain.useCases.SignupUseCase
import com.warrantysafe.app.domain.useCases.SignOutUseCase
import com.warrantysafe.app.presentation.state.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val signupUseCase: SignupUseCase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    val authState: StateFlow<AuthState> = _authState

    // Login User
    fun login(username: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = loginUseCase(username, password)
            if (result.isSuccess) {
                _authState.value = AuthState.Success("Login successful")
            } else {
                _authState.value = AuthState.Error(
                    result.exceptionOrNull()?.message ?: "Login failed due to unknown error"
                )
            }
        }
    }

    // Signup User
    fun signup(imageUri: Uri, username: String, email: String, phoneNo: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = signupUseCase(imageUri, username, email, phoneNo, password)
            if (result.isSuccess) {
                _authState.value = AuthState.Success("Signup successful")
            } else {
                _authState.value = AuthState.Error(
                    result.exceptionOrNull()?.message ?: "Signup failed due to unknown error"
                )
            }
        }
    }

    // SignOut User
    fun signOut() {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = signOutUseCase()
            if (result.isSuccess) {
                _authState.value = AuthState.Success("Sign-out successful")
            } else {
                _authState.value = AuthState.Error(
                    result.exceptionOrNull()?.message ?: "Sign-out failed due to unknown error"
                )
            }
        }
    }
}
