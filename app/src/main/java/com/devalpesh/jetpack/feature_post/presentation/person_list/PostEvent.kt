package com.devalpesh.jetpack.feature_post.presentation.person_list

import com.devalpesh.jetpack.core.util.Event

sealed class PostEvent : Event(){
    object OnLiked : PostEvent()
}