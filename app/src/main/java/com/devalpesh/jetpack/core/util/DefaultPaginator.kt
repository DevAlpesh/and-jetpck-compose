package com.devalpesh.jetpack.core.util

data class DefaultPaginator<T>(
    private val onLoadUpdated: (Boolean) -> Unit,
    private val onRequest: suspend (page: Int) -> Resource<List<T>>,
    private val onError: suspend (UiText) -> Unit,
    private val onSuccess: (List<T>) -> Unit
) : Paginator<T> {

    private var page = 0

    override suspend fun loadNextItems() {
        onLoadUpdated(true)
        when (val result = onRequest(page)) {
            is Resource.Success -> {
                val items = result.data ?: emptyList()
                page++
                onSuccess(items)
                onLoadUpdated(false)
            }

            is Resource.Error -> {
                onError(result.uiText ?: UiText.unknownError())
                onLoadUpdated(true)
            }
        }
    }
}