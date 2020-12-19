package com.example.weanotnew

import android.content.Context

interface BaseView<T> {
    fun setPresenter(presenter: T)
}