package com.devalpesh.jetpack.feature_profile.presentation.edit_profile

import com.devalpesh.jetpack.feature_profile.domain.model.Skill

data class SkillState(
    val skills : List<Skill> = emptyList(),
    val selectedSkills : List<Skill> = emptyList()
)