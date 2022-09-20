package com.devalpesh.jetpack.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.devalpesh.jetpack.presentation.util.states.StandardTextFieldStates
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    
) : ViewModel() {
    
    
    private val _searchState = mutableStateOf<StandardTextFieldStates>(StandardTextFieldStates(""))
    val searchState: State<StandardTextFieldStates> = _searchState
    
    fun setSearchTextState(state:StandardTextFieldStates) {
        _searchState.value = state
    }
    
}