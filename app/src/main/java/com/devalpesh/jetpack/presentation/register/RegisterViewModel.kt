package com.devalpesh.jetpack.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    private val _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword

    fun setShowPassword(showPassword: Boolean) {
        _showPassword.value = showPassword
    }

    private val _emailText = mutableStateOf("")
    val emailText: State<String> = _emailText

    private val _usernameText = mutableStateOf("")
    val usernameText: State<String> = _usernameText

    private val _passwordText = mutableStateOf("")
    val passwordText: State<String> = _passwordText


    private val _emailError = mutableStateOf("")
    val emailError: State<String> = _emailError

    private val _usernameError = mutableStateOf("")
    val userNameError: State<String> = _usernameError

    private val _passwordError = mutableStateOf("")
    val passwordError: State<String> = _passwordError


    private val _isEmailError = mutableStateOf(false)
    val isEmailError: State<Boolean> = _isEmailError

    private val _isUsernameError = mutableStateOf(false)
    val isUsernameError: State<Boolean> = _isUsernameError

    private val _isPasswordError = mutableStateOf(false)
    val isPasswordError: State<Boolean> = _isPasswordError


    fun setEmailText(email: String) {
        _emailText.value = email
    }

    fun setUsernameText(username: String) {
        _usernameText.value = username
    }

    fun setPasswordText(password: String) {
        _passwordText.value = password
    }

    fun setEmailError(error: String) {
        _emailError.value = error
    }

    fun setUsernameError(error: String) {
        _usernameError.value = error
    }

    fun setPasswordError(error: String) {
        _passwordError.value = error
    }

    fun setIsUsernameError(isError: Boolean) {
        _isUsernameError.value = isError
    }

    fun setIsPasswordError(isError: Boolean) {
        _isPasswordError.value = isError
    }

    fun setIsEmailError(error: Boolean) {
        _isEmailError.value = error
    }

}