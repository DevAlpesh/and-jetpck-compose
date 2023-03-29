package com.devalpesh.jetpack.feature_post.presentation.main_feed

import androidx.lifecycle.ViewModel
import com.devalpesh.jetpack.feature_post.domain.use_case.PostUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainFeeViewModel @Inject constructor(
    private val postUseCases: PostUseCases
): ViewModel(){

}