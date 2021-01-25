package com.sonukg.paging.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sonukg.paging.R
import com.sonukg.paging.data.model.RepoModel
import kotlinx.android.synthetic.main.item_list_layout.view.*

class RepoAdapter: PagedListAdapter<RepoModel, RepoAdapter.ItemViewHolder>(REPOS) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutView=LayoutInflater.from(parent.context).inflate(R.layout.item_list_layout,parent,false)
        return ItemViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val repo = getItem(position)
        repo?.let {
            holder.setData(repo)
        }
    }

    class ItemViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView){
        fun setData(repoModel: RepoModel){
            itemView.repo_name.text = repoModel.fullName
            itemView.repo_description.text = repoModel.description
            itemView.repo_language.text = repoModel.language
            itemView.repo_stars.text = repoModel.stars.toString()
            itemView.repo_forks.text = repoModel.forks.toString()
        }
    }

    companion object{
       private val REPOS= object : DiffUtil.ItemCallback<RepoModel>(){
           override fun areItemsTheSame(oldItem: RepoModel, newItem: RepoModel): Boolean =
               oldItem.fullName == newItem.fullName

           @SuppressLint("DiffUtilEquals")
           override fun areContentsTheSame(oldItem: RepoModel, newItem: RepoModel): Boolean =
             oldItem == newItem
       }
    }
}