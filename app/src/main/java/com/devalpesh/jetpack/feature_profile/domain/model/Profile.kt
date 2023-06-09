package com.devalpesh.jetpack.feature_profile.domain.model

data class Profile(
    val userId : String,
    val username: String,
    val bio: String,
    val followingCount: Int,
    val followerCount: Int,
    val postCount: Int,
    val profilePictureUrl: String,
    val topSkills: List<Skill>,
    val bannerUrl : String?,
    val githubUrl: String?,
    val instagramUrl: String?,
    val linkedInUrl: String?,
    val isFollowing: Boolean,
    val isOwnProfile: Boolean
)
