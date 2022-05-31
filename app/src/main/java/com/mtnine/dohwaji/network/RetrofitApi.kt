package com.mtnine.dohwaji.network

import com.mtnine.dohwaji.model.WordData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("dohwaji/getword")
    suspend fun getWord(@Query("page") page: Int?, @Query("limit") limit: Int = 50): WordData

}