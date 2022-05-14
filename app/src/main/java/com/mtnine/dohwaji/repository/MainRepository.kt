package com.mtnine.dohwaji.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mtnine.dohwaji.base.PostPagingSource
import com.mtnine.dohwaji.base.RecyclerPagingSource
import com.mtnine.dohwaji.database.MainDao
import com.mtnine.dohwaji.model.Post
import com.mtnine.dohwaji.model.WordData
import com.mtnine.dohwaji.network.RetrofitApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(private val retrofitApi: RetrofitApi, private val mainDao: MainDao) {
    suspend fun getWord(): WordData = retrofitApi.getWord(0)

    suspend fun getAllPosts(): List<Post> = mainDao.getAllPosts(0)

    fun getRecyclerPagingData(): Flow<PagingData<String>> {
        return Pager(PagingConfig(pageSize = 50)) {
            RecyclerPagingSource(retrofitApi)
        }.flow
    }

    fun getPostPagingData(): Flow<PagingData<Post>> {
        return Pager(PagingConfig(pageSize = 50)) {
            PostPagingSource(mainDao)
        }.flow
    }

    suspend fun insertPost(post: Post) {
        mainDao.insertPost(post)
    }

    suspend fun updatePost(post: Post) {
        mainDao.updatePost(post)
    }

    suspend fun deletePost(post: Post) {
        mainDao.deletePost(post)
    }
}