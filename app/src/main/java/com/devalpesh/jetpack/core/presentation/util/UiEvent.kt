package com.devalpesh.jetpack.core.presentation.util

import com.devalpesh.jetpack.core.util.UiText

sealed class UiEvent {
    data class SnackbarEvent(val uiText: UiText) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
}
