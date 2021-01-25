package com.sonukg.paging.data.network

import com.sonukg.paging.data.model.RepoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RepoService {
    @GET("search/repositories?sort=stars")
    fun getRepository(
            @Query("page") page:Int,
            @Query("per_page") size:Int,
            @Query("q") topic:String,

    ):Call<RepoResponse>
}