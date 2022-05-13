package com.mtnine.dohwaji.repository

import androidx.lifecycle.MutableLiveData
import com.mtnine.dohwaji.model.WordData
import com.mtnine.dohwaji.network.RetrofitApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(private val retrofitApi: RetrofitApi) {
    suspend fun getWord(): WordData = retrofitApi.getWord()
}