package com.devalpesh.jetpack.core.presentation.util

import com.devalpesh.jetpack.core.util.Event
import com.devalpesh.jetpack.core.util.UiText

sealed class UiEvent : Event() {
    data class ShowSnackbar(val uiText: UiText) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    object NavigateUp: UiEvent()
    object OnLogin : UiEvent()
}
