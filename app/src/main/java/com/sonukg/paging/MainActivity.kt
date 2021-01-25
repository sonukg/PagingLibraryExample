package com.sonukg.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sonukg.paging.adapter.RepoAdapter
import com.sonukg.paging.data.model.RepoModel
import com.sonukg.paging.data.network.RepoService
import com.sonukg.paging.viewmodel.RepoViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter=RepoAdapter()
        recycleView.layoutManager=LinearLayoutManager(this)

        val viewModel=ViewModelProviders.of(this).get(RepoViewModel::class.java)
        viewModel.gitRepoPagedList.observe(this, Observer {
            adapter.submitList(it)
        })
        recycleView.adapter=adapter
    }
}