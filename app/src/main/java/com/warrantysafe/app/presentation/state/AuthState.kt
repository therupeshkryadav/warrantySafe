package com.warrantysafe.app.presentation.state

sealed class AuthState {

    object Loading : AuthState() // Represents the loading state
    data class Success(val message: String) : AuthState() // Represents successful login/signup/signout
    data class Error(val errorMessage: String) : AuthState() // Represents an error state with a message
}

