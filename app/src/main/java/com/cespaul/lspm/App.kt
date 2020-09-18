package com.cespaul.lspm

import android.app.Application
import android.content.Context
import com.cespaul.lspm.di.components.AppComponent
import com.cespaul.lspm.di.components.DaggerAppComponent
import com.cespaul.lspm.di.modules.AppModule
import com.cespaul.lspm.di.modules.RoomModule

class App : Application() {
    companion object {
        private lateinit var instance: App

        lateinit var appComponent: AppComponent private set
        val appContext: Context
            get() = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .roomModule(RoomModule())
            .build()
        appComponent.inject(this)
    }
}