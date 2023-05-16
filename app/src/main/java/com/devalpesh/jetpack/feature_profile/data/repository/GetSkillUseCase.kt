package com.devalpesh.jetpack.feature_profile.data.repository

import com.devalpesh.jetpack.core.util.Resource
import com.devalpesh.jetpack.feature_profile.domain.model.Skill
import com.devalpesh.jetpack.core.domain.repository.ProfileRepository

class GetSkillUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke() : Resource<List<Skill>>{
        return repository.getSkills()
    }
}