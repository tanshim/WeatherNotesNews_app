package com.example.weanotnew.news.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Article(
    @SerializedName("source")
    @Expose
    var source: Source? = null,

    @SerializedName("author")
    @Expose
    var author: Any? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("description")
    @Expose
    var description: String? = null,

    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("urlToImage")
    @Expose
    var urlToImage: Any? = null,

    @SerializedName("publishedAt")
    @Expose
    var publishedAt: String? = null,

    @SerializedName("content")
    @Expose
    var content: String? = null
)