package com.devalpesh.jetpack.feature_auth.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devalpesh.jetpack.core.domain.states.StandardTextFieldStates
import com.devalpesh.jetpack.core.presentation.util.UiEvent
import com.devalpesh.jetpack.core.util.Resource
import com.devalpesh.jetpack.core.util.UiText
import com.devalpesh.jetpack.feature_auth.domain.use_case.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _emailStateState =
        mutableStateOf(StandardTextFieldStates())
    val emailStateState: State<StandardTextFieldStates> = _emailStateState

    private val _passwordState = mutableStateOf(StandardTextFieldStates())
    val passwordState: State<StandardTextFieldStates> = this._passwordState

    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredEmail -> {
                _emailStateState.value = _emailStateState.value.copy(
                    text = event.email
                )
            }

            is LoginEvent.EnteredPassword -> {
                _passwordState.value = _passwordState.value.copy(
                    text = event.password
                )
            }

            is LoginEvent.TogglePasswordVisibility -> {
                _loginState.value = _loginState.value.copy(
                    isPasswordVisible = !_loginState.value.isPasswordVisible
                )
            }

            LoginEvent.Login -> {
                login()
            }
        }
    }

    private fun login() {

        _emailStateState.value = _emailStateState.value.copy(
            error = null
        )
        _passwordState.value = _passwordState.value.copy(
            error = null
        )

        _loginState.value = _loginState.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            val loginResult = loginUseCase(
                email = emailStateState.value.text,
                password = passwordState.value.text
            )
            _loginState.value = _loginState.value.copy(
                isLoading = false
            )
            if (loginResult.emailError != null) {
                _emailStateState.value = _emailStateState.value.copy(
                    error = loginResult.emailError
                )
            }
            if (loginResult.passwordError != null) {
                _passwordState.value = _passwordState.value.copy(
                    error = loginResult.passwordError
                )
            }
            when (loginResult.result) {
                is Resource.Success -> {
                    _eventFlow.emit(UiEvent.OnLogin)
                }

                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            loginResult.result.uiText ?: UiText.unknownError()
                        )
                    )
                }

                else -> Unit
            }
        }
    }
}
