package com.example.weanotnew.application

import android.app.Application
import com.example.weanotnew.di.AppComponent
import com.example.weanotnew.di.AppModule
import com.example.weanotnew.di.DaggerAppComponent

class MyApplication: Application() {

    lateinit var appComponent: AppComponent

    private fun initDagger(application: MyApplication): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(application))
            .build()

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }
}