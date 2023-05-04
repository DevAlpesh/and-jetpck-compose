package com.devalpesh.jetpack.feature_profile.presentation.edit_profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.core.domain.states.StandardTextFieldStates
import com.devalpesh.jetpack.core.presentation.util.UiEvent
import com.devalpesh.jetpack.core.util.Resource
import com.devalpesh.jetpack.core.util.UiText
import com.devalpesh.jetpack.feature_profile.domain.use_case.ProfileUseCases
import com.devalpesh.jetpack.feature_profile.presentation.profile.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val profileUseCases: ProfileUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _usernameState = mutableStateOf(StandardTextFieldStates())
    val usernameState: State<StandardTextFieldStates> = _usernameState

    private val _githubTextFieldState = mutableStateOf(StandardTextFieldStates())
    val githubTextFieldState: State<StandardTextFieldStates> = _githubTextFieldState

    private val _instagramTextFieldState = mutableStateOf(StandardTextFieldStates())
    val instagramTextFieldState: State<StandardTextFieldStates> = _instagramTextFieldState

    private val _linkedInTextFieldState = mutableStateOf(StandardTextFieldStates())
    val linkedInTextFieldState: State<StandardTextFieldStates> = _linkedInTextFieldState

    private val _bioState = mutableStateOf(StandardTextFieldStates())
    val bioState: State<StandardTextFieldStates> = _bioState

    private val _skills = mutableStateOf(SkillState())
    val skills: State<SkillState> = _skills

    private val _profileState = mutableStateOf(ProfileState())
    val profileState: State<ProfileState> = _profileState

    init {
        savedStateHandle.get<String>("userId")?.let { userId ->
            getSkills()
            getProfile(userId)
        }
    }

    private fun getSkills() {
        viewModelScope.launch {
            when (val result = profileUseCases.getSkills()) {
                is Resource.Success -> {
                    _skills.value = skills.value.copy(
                        skills = result.data ?: kotlin.run {
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    UiText.StringResource(R.string.txt_error_couldnt_load_skills)
                                )
                            )
                            return@launch
                        }
                    )
                }

                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            UiText.StringResource(R.string.txt_error_couldnt_load_skills)
                        )
                    )
                    return@launch
                }
            }
        }
    }

    private fun getProfile(userId: String) {
        viewModelScope.launch {
            _profileState.value = profileState.value.copy(
                isLoading = true
            )
            when (val result = profileUseCases.getProfile(userId = userId)) {
                is Resource.Success -> {
                    val profile = result.data ?: kotlin.run {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                UiText.StringResource(R.string.txt_error_couldnt_load_profile)
                            )
                        )
                        return@launch
                    }
                    _usernameState.value = usernameState.value.copy(
                        text = profile.username
                    )
                    _githubTextFieldState.value = githubTextFieldState.value.copy(
                        text = profile.githubUrl ?: ""
                    )
                    _instagramTextFieldState.value = instagramTextFieldState.value.copy(
                        text = profile.instagramUrl ?: ""
                    )
                    _linkedInTextFieldState.value = linkedInTextFieldState.value.copy(
                        text = profile.linkedInUrl ?: ""
                    )
                    _bioState.value = bioState.value.copy(
                        text = profile.bio
                    )
                    _skills.value = skills.value.copy(
                        selectedSkills = profile.topSkills
                    )
                    _profileState.value = profileState.value.copy(
                        isLoading = false,
                        profile = profile
                    )
                }

                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(result.uiText ?: UiText.unknownError())
                    )
                    return@launch
                }
            }
        }
    }

    fun onEvent(event: EditProfileEvent) {
        when (event) {
            is EditProfileEvent.EnteredUsername -> {
                _usernameState.value = usernameState.value.copy(
                    text = event.value
                )
            }

            is EditProfileEvent.EnteredGithubUrl -> {
                _githubTextFieldState.value = githubTextFieldState.value.copy(
                    text = event.value
                )
            }

            is EditProfileEvent.EnteredInstagramUrl -> {
                _instagramTextFieldState.value = instagramTextFieldState.value.copy(
                    text = event.value
                )
            }

            is EditProfileEvent.EnteredLinkedInUrl -> {
                _linkedInTextFieldState.value = linkedInTextFieldState.value.copy(
                    text = event.value
                )
            }

            is EditProfileEvent.EnteredBio -> {
                _bioState.value = bioState.value.copy(
                    text = event.value
                )
            }

            is EditProfileEvent.CropProfilePicture -> {

            }

            is EditProfileEvent.CropBannerImage -> {

            }

            is EditProfileEvent.SetSkillSelected -> {

            }

            is EditProfileEvent.UpdateProfile -> {

            }
        }
    }
}