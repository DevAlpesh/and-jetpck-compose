package com.devalpesh.jetpack.feature_post.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.devalpesh.jetpack.feature_post.data.remote.PostApi
import com.devalpesh.jetpack.core.domain.models.Post
import com.devalpesh.jetpack.core.util.Constants
import retrofit2.HttpException
import java.io.IOException

class PostSource(
    private val api: PostApi,
    private val source: Source
) : PagingSource<Int, Post>() {

    private var currentPage = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val nextPage = params.key ?: currentPage
            val posts = when (source) {
                is Source.Follows -> api.getPostsForFollows(
                    page = nextPage,
                    pageSize = Constants.DEFAULT_PAGE_SIZE
                )

                is Source.Profile -> api.getPostForProfile(
                    userId = source.userId,
                    page = nextPage,
                    pageSize = Constants.DEFAULT_PAGE_SIZE
                )
            }

            LoadResult.Page(
                data = posts,
                prevKey = if (nextPage == 0) null else nextPage - 1,
                nextKey = if (posts.isEmpty()) null else currentPage + 1
            ).also { currentPage++ }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition
    }

    sealed class Source {
        data class Profile(val userId: String) : Source()
        object Follows : Source()
    }
}