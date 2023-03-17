package com.devalpesh.jetpack.feature_profile.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _toolbarStates = mutableStateOf(ProfileToolbarState())
    val toolbarStates: State<ProfileToolbarState> = _toolbarStates

    fun setExpandedRation(ratio: Float) {
        _toolbarStates.value = _toolbarStates.value.copy(expandedRatio = ratio)
    }

    fun setToolbarOffsetY(value: Float) {
        _toolbarStates.value = _toolbarStates.value.copy(toolbarOffsetY = value)
    }
}