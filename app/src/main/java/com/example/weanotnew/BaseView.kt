package com.example.weanotnew

interface BaseView<T> {
    fun setPresenter(presenter: T)
}