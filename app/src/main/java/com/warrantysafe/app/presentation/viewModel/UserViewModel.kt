package com.warrantysafe.app.presentation.viewModel

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
import com.warrantysafe.app.domain.utils.Results
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

    private val _loginState = MutableLiveData<Results<User>>()
    val loginState: LiveData<Results<User>> get() = _loginState

    private val _signUpState = MutableLiveData<Results<User>>()
    val signUpState: LiveData<Results<User>> get() = _signUpState

    // LiveData to emit the initial navigation route
    private val _navigationRoute = MutableLiveData<String>()
    val navigationRoute: LiveData<String> get() = _navigationRoute

    private val _userState = MutableLiveData<Results<User>>()
    val userState: LiveData<Results<User>> get() = _userState

    private val _signOutState = MutableLiveData<Results<Unit>>()
    val signOutState: LiveData<Results<Unit>> get() = _signOutState

    private val _updateUserState = MutableLiveData<Results<User>>()
    val updateUserState: LiveData<Results<User>> get() = _updateUserState

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    // Check if user is logged in and set the initial route
    fun checkUser() {
        viewModelScope.launch {
            val route = checkUserUseCase()
            _navigationRoute.value = route
        }
    }

    // Handle user sign-up
    fun signUpUser(user: User) {
        viewModelScope.launch {
            try {
                _isLoading.value = true // Start loading
                val result = signUpUserUseCase.invoke(user)
                _signUpState.value = result
                _isLoading.value = false // Stop loading
            } catch (e: Exception) {
                _signUpState.value = Results.Failure(e)
            }
        }
    }

    // Handle user login
    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true // Start loading
                val result = loginUserUseCase.invoke(email, password)
                _loginState.value = result
                _isLoading.value = false // Stop loading
            } catch (e: Exception) {

                _loginState.value = Results.Failure(e)
            }
        }
    }

    // Get user details
    fun getUser() {
        viewModelScope.launch {
            _userState.value = Results.Loading
            try {
                val result = getUserUseCase.invoke()
                _userState.value = result
            } catch (e: Exception) {
                _userState.value = Results.Failure(e)
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
                _updateUserState.value = Results.Failure(e)
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
                _signOutState.value = Results.Failure(e) // Notify the UI about failure
            }
        }
    }

}
