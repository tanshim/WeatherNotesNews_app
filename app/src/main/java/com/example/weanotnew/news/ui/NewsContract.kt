package com.example.weanotnew.news.ui

import android.content.Context
import com.example.weanotnew.BasePresenter
import com.example.weanotnew.BaseView
import com.example.weanotnew.news.model.Article


interface NewsContract {

    interface NewsPresenter: BasePresenter {
        fun onViewCreated()
    }

    interface NewsView: BaseView<NewsPresenter> {
        fun setViews(articles: List<Article>?)
    }
}