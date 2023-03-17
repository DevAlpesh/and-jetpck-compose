package com.devalpesh.jetpack.feature_post.presentation.postdetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.devalpesh.jetpack.core.domain.states.StandardTextFieldStates
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(

) : ViewModel() {

    private val _descriptionState = mutableStateOf(StandardTextFieldStates(""))
    val descriptionState: State<StandardTextFieldStates> = _descriptionState

    fun setDescriptionState(state: StandardTextFieldStates) {
        _descriptionState.value = state
    }

}