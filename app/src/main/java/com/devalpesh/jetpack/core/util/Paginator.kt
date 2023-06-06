package com.devalpesh.jetpack.core.util

interface Paginator<T> {
    suspend fun loadNextItems()
}