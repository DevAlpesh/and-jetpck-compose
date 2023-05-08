package com.devalpesh.jetpack.feature_profile.data.remote.response

import com.devalpesh.jetpack.feature_profile.domain.model.Profile

data class ProfileResponse(
    val userId: String,
    val username: String,
    val bio: String,
    val followingCount: Int,
    val followerCount: Int,
    val postCount: Int,
    val profilePictureUrl: String,
    val topSkills: List<SkillDto>,
    val bannerUrl: String,
    val githubUrl: String?,
    val instagramUrl: String?,
    val linkedInUrl: String?,
    val isFollowing: Boolean,
    val isOwnProfile: Boolean
) {
    fun toProfile(): Profile {
        return Profile(
            userId,
            username,
            bio,
            followingCount,
            followerCount,
            postCount,
            profilePictureUrl,
            topSkills.map { it.toSkill() },
            bannerUrl,
            githubUrl,
            instagramUrl,
            linkedInUrl,
            isFollowing,
            isOwnProfile
        )
    }
}