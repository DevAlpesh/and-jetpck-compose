package com.devalpesh.jetpack.feature_profile.domain.use_case

import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.core.util.Resource
import com.devalpesh.jetpack.core.util.UiText
import com.devalpesh.jetpack.feature_profile.domain.model.Skill
import com.devalpesh.jetpack.feature_profile.domain.util.ProfileConstants

class SetSkillSelectedUseCase {
    operator fun invoke(
        selectedSkill: List<Skill>,
        skillToToggle: Skill
    ): Resource<List<Skill>> {
        if (skillToToggle in selectedSkill) {
            return Resource.Success(selectedSkill - skillToToggle)
        }
        return if (selectedSkill.size >= ProfileConstants.MAX_SELECTED_SKILL_COUNT) {
            Resource.Error(uiText = UiText.StringResource(R.string.txt_error_msg_max_skills_selected))
        } else {
            Resource.Success(selectedSkill + skillToToggle)
        }
    }
}