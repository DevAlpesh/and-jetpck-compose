package com.devalpesh.jetpack.feature_profile.presentation.search

import com.devalpesh.jetpack.core.util.AppError
import com.devalpesh.jetpack.core.util.UiText

class SearchError(
    val message : UiText,
) : AppError()