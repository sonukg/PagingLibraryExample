package com.sonukg.paging.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RepoServiceBuilder {
    private val baseUrl:String="https://api.github.com/"
    private val logger=HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val okHttpClient=OkHttpClient.Builder().addInterceptor(logger)

    private val retroBuilder=Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient.build())

    private val retrofit= retroBuilder.build()

    fun <T> buildService(serviceType:Class<T>):T{
        return retrofit.create(serviceType)
    }
}