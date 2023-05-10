package com.devalpesh.jetpack.feature_profile.domain.use_case

import android.net.Uri
import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.core.util.Resource
import com.devalpesh.jetpack.core.util.SimpleResources
import com.devalpesh.jetpack.core.util.UiText
import com.devalpesh.jetpack.feature_profile.domain.model.UpdateProfileData
import com.devalpesh.jetpack.feature_profile.domain.repository.ProfileRepository
import com.devalpesh.jetpack.feature_profile.domain.util.ProfileConstants

class UpdateProfileUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(
        updateProfileData: UpdateProfileData,
        profilePictureUri: Uri?,
        bannerUri: Uri?
    ): SimpleResources {

        if (updateProfileData.username.isBlank()) {
            return Resource.Error(
                uiText = UiText.StringResource(R.string.txt_error_username_empty)
            )
        }
        val isValidGithubUrl =
            ProfileConstants.GITHUB_PROFILE_REGEX.matches(updateProfileData.githubUrl)

        if (!isValidGithubUrl) {
            return Resource.Error(
                uiText = UiText.StringResource(R.string.txt_error_github_url)
            )
        }


        val isValidInstagramUrl =
            ProfileConstants.INSTAGRAM_PROFILE_REGEX.matches(updateProfileData.instagramUrl)

        if (!isValidInstagramUrl) {
            return Resource.Error(
                uiText = UiText.StringResource(R.string.txt_error_instagram_url)
            )
        }


        val isValidLinkedInUrl =
            ProfileConstants.LINKED_IN_PROFILE_REGEX.matches(updateProfileData.linkedInUrl)

        if (!isValidLinkedInUrl) {
            return Resource.Error(
                uiText = UiText.StringResource(R.string.txt_error_linked_in_url)
            )
        }

        return repository.updateProfile(
            updateProfileData = updateProfileData,
            profilePictureUri = profilePictureUri,
            bannerImageUri = bannerUri
        )
    }
}