package com.cespaul.lspm.di.modules

import android.content.Context
import com.cespaul.lspm.App
import com.cespaul.lspm.di.components.AppScope
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: App) {

    @AppScope
    @Provides
    fun provideContext(): Context = app.applicationContext
}