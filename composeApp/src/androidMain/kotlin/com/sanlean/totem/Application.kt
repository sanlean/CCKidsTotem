package com.sanlean.totem

import android.app.Application
import android.content.Context

class Application: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    companion object {
        private var appContext: Context? = null

        fun getContext(): Context? {
            return appContext
        }
    }

}