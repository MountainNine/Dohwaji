package com.mtnine.dohwaji.network

import com.mtnine.dohwaji.model.WordData
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {

    @GET("dohwaji/getword")
    suspend fun getWord(): WordData

}