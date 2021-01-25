package com.sonukg.paging.datasource

import androidx.paging.PageKeyedDataSource
import com.sonukg.paging.data.model.RepoModel
import com.sonukg.paging.data.model.RepoResponse
import com.sonukg.paging.data.network.RepoService
import com.sonukg.paging.data.network.RepoServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoDataSource:PageKeyedDataSource<Int,RepoModel>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, RepoModel>
    ) {
        val service = RepoServiceBuilder.buildService(RepoService::class.java)
        val call = service.getRepository(FIRST_PAGE, PAGE_SIZE,TOPIC)

        call.enqueue(object : Callback<RepoResponse>{
            override fun onResponse(call: Call<RepoResponse>, response: Response<RepoResponse>) {
                if (response.isSuccessful) {

                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.items

                    responseItems?.let {
                        callback.onResult(responseItems, null, FIRST_PAGE + 1)
                    }
                }
            }

            override fun onFailure(call: Call<RepoResponse>, t: Throwable) {

            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RepoModel>) {
        val service = RepoServiceBuilder.buildService(RepoService::class.java)
        val call = service.getRepository(params.key, PAGE_SIZE,TOPIC)

        call.enqueue(object : Callback<RepoResponse> {

            override fun onResponse(call: Call<RepoResponse>, response: Response<RepoResponse>) {

                if (response.isSuccessful) {

                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.items

                    val key = if (params.key > 1) params.key - 1 else 0

                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }

            override fun onFailure(call: Call<RepoResponse>, t: Throwable) {
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RepoModel>) {
        val service = RepoServiceBuilder.buildService(RepoService::class.java)
        val call = service.getRepository(FIRST_PAGE, PAGE_SIZE,TOPIC)
        call.enqueue(object : Callback<RepoResponse> {

            override fun onResponse(call: Call<RepoResponse>, response: Response<RepoResponse>) {

                if (response.isSuccessful) {

                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.items

                    val key = if (apiResponse.totalCount > params.key) params.key + 1 else apiResponse.totalCount

                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }

            override fun onFailure(call: Call<RepoResponse>, t: Throwable) {
            }
        })
    }

    companion object {
        const val PAGE_SIZE = 100
        const val FIRST_PAGE = 1
        const val TOPIC = "android"
    }
}