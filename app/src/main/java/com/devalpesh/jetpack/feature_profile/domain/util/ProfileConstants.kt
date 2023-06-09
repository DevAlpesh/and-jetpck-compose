package com.devalpesh.jetpack.feature_profile.domain.util

object ProfileConstants {
    const val MAX_SELECTED_SKILL_COUNT = 3
    const val SEARCH_DELAY = 500L

    val GITHUB_PROFILE_REGEX = "(https://)?(www\\.)?github\\.com/[A-z0-9_-]+/?".toRegex()
    val INSTAGRAM_PROFILE_REGEX = "(https?://)?(www\\.)?instagram\\.com/[a-z_?=.\\-A-Z0-9]*".toRegex()
    val LINKED_IN_PROFILE_REGEX = "http(s)?://(\\w+\\.)?linkedin\\.com/in/[A-z0-9_-]+/?".toRegex()
}
/*
https://www.github.com/anand
https://www.instagram.com/anand
https://www.linkedin.com/in/anand*/
