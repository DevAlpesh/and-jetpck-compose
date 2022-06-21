package com.devalpesh.jetpack.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _usernameText = mutableStateOf("")
    val usernameText: State<String> = _usernameText

    private val _passwordText = mutableStateOf("")
    val passwordText: State<String> = _passwordText

    fun setUserName(username: String) {
        _usernameText.value = username
    }

    fun setPassword(password: String) {
        _passwordText.value = password
    }

}