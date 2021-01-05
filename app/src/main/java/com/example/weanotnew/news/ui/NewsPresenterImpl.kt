package com.example.weanotnew.news.ui

import android.content.Context
import android.util.Log
import com.example.weanotnew.news.api.NewsApiFactory
import com.example.weanotnew.news.model.Article
import com.example.weanotnew.news.model.ArticlesResponse
import com.example.weanotnew.util.NEWS_API_KEY

class NewsPresenterImpl(view: NewsContract.NewsView, context: Context): NewsContract.NewsPresenter {

    private var view: NewsContract.NewsView? = view

    private var articlesResponse: ArticlesResponse? = null
    private var articlesList: List<Article>? = null

    override fun onViewCreated() {
        loadNews()
    }

    private fun loadNews() {
        articlesResponse = NewsApiFactory.newsApiService.getTrendingNews(apiKey = NEWS_API_KEY)
            .execute()
            .body()
            .apply {
                articlesList = this?.articles.apply {
                    view?.setViews(this)
                }
            }
    }

    override fun onDestroy() {
        this.view = null
    }
}