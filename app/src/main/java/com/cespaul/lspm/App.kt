package com.cespaul.lspm

import android.app.Application
import android.content.Context
import com.cespaul.lspm.di.components.AppComponent
import com.cespaul.lspm.di.components.DaggerAppComponent
import com.cespaul.lspm.di.modules.AppModule
import com.mapbox.mapboxsdk.Mapbox

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
            .factory()
            .create(AppModule(this))
        appComponent.inject(this)

        val mapToken: String = appContext.getString(R.string.map_token)
        Mapbox.getInstance(appContext, mapToken)
    }
}