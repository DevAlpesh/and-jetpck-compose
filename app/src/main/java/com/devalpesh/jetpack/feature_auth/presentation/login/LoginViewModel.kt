package com.devalpesh.jetpack.feature_auth.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword

    private val _usernameText = mutableStateOf("")
    val usernameText: State<String> = _usernameText

    private val _passwordText = mutableStateOf("")
    val passwordText: State<String> = _passwordText

    private val _isUsernameError = mutableStateOf(false)
    val isUsernameError: State<Boolean> = _isUsernameError

    private val _isPasswordError = mutableStateOf(false)
    val isPasswordError: State<Boolean> = _isPasswordError

    private val _usernameError = mutableStateOf("")
    val userNameError: State<String> = _usernameError

    private val _passwordError = mutableStateOf("")
    val passwordError: State<String> = _passwordError

    fun setShowPassword(showPassword: Boolean) {
        _showPassword.value = showPassword
    }

    fun setUsernameText(username: String) {
        _usernameText.value = username
    }

    fun setPasswordText(password: String) {
        _passwordText.value = password
    }

    fun setPasswordError(error: String) {
        _passwordError.value = error
    }

    fun setUsernameError(error: String) {
        _usernameError.value = error
    }

    fun setIsUsernameError(isError: Boolean) {
        _isUsernameError.value = isError
    }

    fun setIsPasswordError(isError: Boolean) {
        _isPasswordError.value = isError
    }

}