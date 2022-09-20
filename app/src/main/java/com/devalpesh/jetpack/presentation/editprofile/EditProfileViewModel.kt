package com.devalpesh.jetpack.presentation.editprofile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.devalpesh.jetpack.presentation.util.states.StandardTextFieldStates
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(

) : ViewModel() {
    private val _usernameState = mutableStateOf<StandardTextFieldStates>(StandardTextFieldStates())
    val usernameState: State<StandardTextFieldStates> = _usernameState

    private val _githubTextFieldState = mutableStateOf<StandardTextFieldStates>(
        StandardTextFieldStates()
    )
    val githubTextFieldState: State<StandardTextFieldStates> = _githubTextFieldState

    private val _instagramTextFieldState = mutableStateOf<StandardTextFieldStates>(
        StandardTextFieldStates()
    )
    val instagramTextFieldState: State<StandardTextFieldStates> = _instagramTextFieldState

    private val _linkedInTextFieldState = mutableStateOf<StandardTextFieldStates>(
        StandardTextFieldStates()
    )
    val linkedInTextFieldState: State<StandardTextFieldStates> = _linkedInTextFieldState

    private val _bioState = mutableStateOf<StandardTextFieldStates>(StandardTextFieldStates())
    val bioState: State<StandardTextFieldStates> = _bioState

    fun setBioState(state: StandardTextFieldStates) {
        _bioState.value = state
    }

    fun setLinkedInTextFieldState(state: StandardTextFieldStates) {
        _linkedInTextFieldState.value = state
    }

    fun setInstagramTextFieldState(state: StandardTextFieldStates) {
        _instagramTextFieldState.value = state
    }

    fun setGithubTextFieldState(state: StandardTextFieldStates) {
        _githubTextFieldState.value = state
    }

    fun setUsernameState(state: StandardTextFieldStates) {
        _usernameState.value = state
    }


}