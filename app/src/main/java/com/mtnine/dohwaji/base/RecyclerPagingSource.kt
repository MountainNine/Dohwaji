package com.mtnine.dohwaji.base

import android.util.Log
import androidx.paging.*
import com.mtnine.dohwaji.network.RetrofitApi
import java.lang.Exception
import javax.inject.Inject

class RecyclerPagingSource @Inject constructor(val retrofitApi: RetrofitApi) :
    PagingSource<Int, String>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
        try {
            val page = params.key ?: 0
            val response = retrofitApi.getWord(page).words
            val nextPage = if(response.count() == 50) page+1 else null
            return LoadResult.Page(data = response, prevKey = null, nextKey = nextPage)
        } catch(e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, String>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}