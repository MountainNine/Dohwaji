package com.mtnine.dohwaji.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mtnine.dohwaji.base.RecyclerPagingSource
import com.mtnine.dohwaji.model.WordData
import com.mtnine.dohwaji.network.RetrofitApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(private val retrofitApi: RetrofitApi) {
    suspend fun getWord(): WordData = retrofitApi.getWord(0)

    fun getPagingData(): Flow<PagingData<String>> {
        return Pager(PagingConfig(pageSize = 50)) {
            RecyclerPagingSource(retrofitApi)
        }.flow
    }
}