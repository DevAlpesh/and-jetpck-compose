package com.devalpesh.jetpack.feature_profile.domain.model


data class UpdateProfileData(
    val username: String,
    val bio: String,
    val githubUrl: String,
    val instagramUrl: String,
    val linkedInUrl: String,
    val skills: List<Skill>
)
