package com.example.retrofit_livedata_glide.service

import com.example.retrofit_livedata_glide.model.RecyclerList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET("repositories")//repositories?q=newyork
    fun getDataFromApıToString(@Query("q") query: String): Call<RecyclerList>


}