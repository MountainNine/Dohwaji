package com.mtnine.dohwaji.repository

import androidx.paging.*
import com.mtnine.dohwaji.database.MainDao
import com.mtnine.dohwaji.model.Post
import java.lang.Exception
import javax.inject.Inject

class PostPagingSource @Inject constructor(val mainDao: MainDao) :
    PagingSource<Int, Post>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> =
        try {
            val page = params.key ?: 0
            val response = mainDao.getAllPosts(page, 50)
            val nextPage = if (response.count() == 50) page + 1 else null
            LoadResult.Page(data = response, prevKey = null, nextKey = nextPage)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    override fun getRefreshKey(state: PagingState<Int, Post>) =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
}