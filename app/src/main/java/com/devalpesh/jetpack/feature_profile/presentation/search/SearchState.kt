package com.devalpesh.jetpack.feature_profile.presentation.search

import com.devalpesh.jetpack.core.domain.models.UserItem

data class SearchState(
    val userItem : List<UserItem> = emptyList(),
    val isLoading : Boolean= false
)