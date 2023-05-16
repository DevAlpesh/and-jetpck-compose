package com.devalpesh.jetpack.feature_post.presentation.person_list

import com.devalpesh.jetpack.core.domain.models.UserItem

data class PersonListState(
    val users : List<UserItem> = emptyList(),
    val isLoading : Boolean = false
)
