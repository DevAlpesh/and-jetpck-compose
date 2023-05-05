package com.devalpesh.jetpack.feature_profile.domain.use_case

import com.devalpesh.jetpack.feature_profile.data.repository.GetSkillUseCase

data class ProfileUseCases(
    val getProfile: GetProfileUseCase,
    val getSkills : GetSkillUseCase,
    val updateProfile: UpdateProfileUseCase
) {
}