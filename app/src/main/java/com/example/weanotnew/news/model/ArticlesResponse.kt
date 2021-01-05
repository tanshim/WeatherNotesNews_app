package com.example.weanotnew.news.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class ArticlesResponse(
    @SerializedName("status")
    @Expose
    var status: String? = null,

    @SerializedName("totalResults")
    @Expose
    var totalResults: Int? = null,

    @SerializedName("articles")
    @Expose
    var articles: List<Article>? = null
)