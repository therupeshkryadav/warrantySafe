package com.warrantysafe.app.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.warrantysafe.app.domain.model.User
import com.warrantysafe.app.domain.useCases.CheckUserUseCase
import com.warrantysafe.app.domain.useCases.GetUserUseCase
import com.warrantysafe.app.domain.useCases.LoginUserUseCase
import com.warrantysafe.app.domain.useCases.SignOutUserUseCase
import com.warrantysafe.app.domain.useCases.SignUpUserUseCase
import com.warrantysafe.app.domain.useCases.UpdateUserUseCase
import com.warrantysafe.app.presentation.navigation.Route
import kotlinx.coroutines.launch

class UserViewModel(
    private val signUpUserUseCase: SignUpUserUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val checkUserUseCase: CheckUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val signOutUserUseCase: SignOutUserUseCase
) : ViewModel() {

    private val _loginState = MutableLiveData<Result<User>>()
    val loginState: LiveData<Result<User>> get() = _loginState

    private val _signUpState = MutableLiveData<Result<User>>()
    val signUpState: LiveData<Result<User>> get() = _signUpState

    // LiveData to emit the initial navigation route
    private val _navigationRoute = MutableLiveData<String>()
    val navigationRoute: LiveData<String> get() = _navigationRoute

    private val _userState = MutableLiveData<Result<User>>()
    val userState: LiveData<Result<User>> get() = _userState

    private val _signOutState = MutableLiveData<Result<Unit>>()
    val signOutState: LiveData<Result<Unit>> get() = _signOutState

    private val _updateUserState = MutableLiveData<Result<User>>()
    val updateUserState: LiveData<Result<User>> get() = _updateUserState

    // Check if user is logged in and set the initial route
    fun checkUser() {
        viewModelScope.launch {
            val isUserLoggedIn = checkUserUseCase()
            _navigationRoute.value = if (isUserLoggedIn) {
                Route.HomeScreen.route
            } else {
                Route.LoginScreen.route
            }
        }
    }

    // Handle user sign-up
    fun signUpUser(user: User) {
        viewModelScope.launch {
            try {
                val result = signUpUserUseCase.invoke(user)
                _signUpState.value = result
            } catch (e: Exception) {
                _signUpState.value = Result.failure(e)
            }
        }
    }

    // Handle user login
    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = loginUserUseCase.invoke(email, password)
                _loginState.value = result
            } catch (e: Exception) {
                _loginState.value = Result.failure(e)
            }
        }
    }

    // Get user details
    fun getUser() {
        viewModelScope.launch {
            try {
                val result = getUserUseCase.invoke()
                _userState.value = result
            } catch (e: Exception) {
                _userState.value = Result.failure(e)
            }
        }
    }

    // Update User details
    fun updateUser(user: User){
        viewModelScope.launch {
            try {
                val result = updateUserUseCase.invoke(user)
                _updateUserState.value = result
            } catch (e: Exception) {
                _updateUserState.value = Result.failure(e)
            }
        }
    }

    // Sign-out user
    fun signOutUser() {
        viewModelScope.launch {
            try {
                val result = signOutUserUseCase.invoke()
                _signOutState.value = result // Notify the UI about success
            } catch (e: Exception) {
                _signOutState.value = Result.failure(e) // Notify the UI about failure
            }
        }
    }

}
