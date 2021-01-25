package com.sonukg.paging.data.model

import com.google.gson.annotations.SerializedName

class RepoModel(
    @field:SerializedName("full_name")
    var fullName:String?=null,

    val description:String?=null,

    @SerializedName("stargazers_count")
    val stars:Int=0,

    @SerializedName("forks_count")
    val forks:Int=0,

    @SerializedName("language")
    val language:String?=null

)

class RepoResponse {

    @field:SerializedName("total_count")
    var totalCount: Int = 0

    var items: List<RepoModel>? = null
}