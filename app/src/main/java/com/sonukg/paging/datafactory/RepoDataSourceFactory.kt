package com.sonukg.paging.datafactory

import androidx.lifecycle.MutableLiveData
import com.sonukg.paging.data.model.RepoModel
import androidx.paging.DataSource
import com.sonukg.paging.datasource.RepoDataSource


class RepoDataSourceFactory : DataSource.Factory<Int,RepoModel>() {
    val gitRepoLiveDataSource = MutableLiveData<RepoDataSource>()
    override fun create(): DataSource<Int, RepoModel> {
        val repoDataSource = RepoDataSource()
        gitRepoLiveDataSource.postValue(repoDataSource)
        return repoDataSource
    }
}