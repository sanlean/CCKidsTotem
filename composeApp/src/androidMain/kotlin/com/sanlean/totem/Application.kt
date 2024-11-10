package com.sanlean.totem

import android.app.Application
import android.content.Context

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    companion object {
        private lateinit var appContext: Context

        fun getContext(): Context {
            return appContext
        }
    }
}
