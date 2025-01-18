package com.warrantysafe.app.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.model.User
import com.warrantysafe.app.domain.useCases.CheckUserUseCase
import com.warrantysafe.app.domain.useCases.LoginUserUseCase
import com.warrantysafe.app.domain.useCases.SignOutUserUseCase
import com.warrantysafe.app.domain.useCases.SignUpUserUseCase
import com.warrantysafe.app.domain.useCases.UpdateUserUseCase
import kotlinx.coroutines.launch

class UserViewModel(
    private val checkUserUseCase: CheckUserUseCase,
    private val signUpUserUseCase: SignUpUserUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val signOutUserUseCase: SignOutUserUseCase
) : ViewModel() {

    private val _loginState = MutableLiveData<Result<User>>()
    val loginState: LiveData<Result<User>> get() = _loginState

    private val _signUpState = MutableLiveData<Result<User>>()
    val signUpState: LiveData<Result<User>> get() = _signUpState


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
}
