package com.sonukg.paging.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sonukg.paging.data.model.RepoModel
import com.sonukg.paging.datafactory.RepoDataSourceFactory
import com.sonukg.paging.datasource.RepoDataSource

class RepoViewModel:ViewModel() {
    var gitRepoPagedList: LiveData<PagedList<RepoModel>>
    private var liveDataSource: LiveData<RepoDataSource>
    init {
        val itemDataSourceFactory = RepoDataSourceFactory()
        liveDataSource = itemDataSourceFactory.gitRepoLiveDataSource

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(RepoDataSource.PAGE_SIZE)
            .build()

        gitRepoPagedList = LivePagedListBuilder(itemDataSourceFactory, config)
            .build()
    }
}