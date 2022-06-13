package com.mtnine.dohwaji.repository

import androidx.paging.*
import com.mtnine.dohwaji.network.RetrofitApi
import java.lang.Exception
import javax.inject.Inject

class RecyclerPagingSource @Inject constructor(val retrofitApi: RetrofitApi) :
    PagingSource<Int, String>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> =
        try {
            val page = params.key ?: 0
            val response = retrofitApi.getWord(page).words
            val nextPage = if (response.count() == 50) page + 1 else null
            LoadResult.Page(data = response, prevKey = null, nextKey = nextPage)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }


    override fun getRefreshKey(state: PagingState<Int, String>) =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
}