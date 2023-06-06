package com.devalpesh.jetpack.feature_profile.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devalpesh.jetpack.core.domain.models.Post
import com.devalpesh.jetpack.core.domain.use_case.GetOwnUserIdUseCase
import com.devalpesh.jetpack.core.presentation.PagingState
import com.devalpesh.jetpack.core.presentation.util.UiEvent
import com.devalpesh.jetpack.core.util.DefaultPaginator
import com.devalpesh.jetpack.core.util.DefaultPostLiker
import com.devalpesh.jetpack.core.util.Event
import com.devalpesh.jetpack.core.util.ParentType
import com.devalpesh.jetpack.core.util.Resource
import com.devalpesh.jetpack.core.util.UiText
import com.devalpesh.jetpack.feature_post.domain.use_case.PostUseCases
import com.devalpesh.jetpack.feature_profile.domain.use_case.ProfileUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCases: ProfileUseCases,
    private val postUseCases: PostUseCases,
    private val getOwnUserId: GetOwnUserIdUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val postLiker: DefaultPostLiker
) : ViewModel() {

    private val _toolbarStates = mutableStateOf(ProfileToolbarState())
    val toolbarStates: State<ProfileToolbarState> = _toolbarStates

    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _pagingState = mutableStateOf<PagingState<Post>>(PagingState())
    val pagingState: State<PagingState<Post>> = _pagingState

    private val paginator = DefaultPaginator(
        onLoadUpdated = { isLoading ->
            _pagingState.value = pagingState.value.copy(
                isLoading = isLoading
            )
        },
        onRequest = { page ->
            val userId = savedStateHandle.get<String>("userId") ?: getOwnUserId()
            profileUseCases.getPostsForProfile(
                userId = userId,
                page = page
            )
        },
        onSuccess = { posts ->
            _pagingState.value = pagingState.value.copy(
                items = pagingState.value.items + posts,
                endReached = posts.isEmpty(),
                isLoading = false
            )
        },
        onError = { uiText ->
            _eventFlow.emit(
                UiEvent.ShowSnackbar(uiText)
            )
        }
    )

    init {
        loadNextPost()
    }

    fun setExpandedRation(ratio: Float) {
        _toolbarStates.value = _toolbarStates.value.copy(expandedRatio = ratio)
    }

    fun setToolbarOffsetY(value: Float) {
        _toolbarStates.value = _toolbarStates.value.copy(toolbarOffsetY = value)
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.GetProfile -> {

            }

            is ProfileEvent.LikePost -> {
                viewModelScope.launch {
                    toggleLikeForParent(
                        parentId = event.postId
                    )
                }

            }
        }
    }

    fun loadNextPost() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    private fun toggleLikeForParent(
        parentId: String
    ) {
        viewModelScope.launch {
            postLiker.toggleLike(
                posts= pagingState.value.items,
                parentId = parentId,
                onRequest = {isLiked->
                    postUseCases.toggleLikeForParent(
                        parentId = parentId,
                        parentType = ParentType.Post.type,
                        isLiked = isLiked
                    )
                },
                onStateUpdated = {posts ->
                    _pagingState.value = pagingState.value.copy(
                        items = posts
                    )
                }
            )
        }
    }

    fun getProfile(userId: String?) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            when (val result = profileUseCases.getProfile(
                userId ?: getOwnUserId()
            )) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        profile = result.data,
                        isLoading = false
                    )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false
                    )
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            uiText = result.uiText ?: UiText.unknownError()
                        )
                    )
                }
            }
        }
    }
}