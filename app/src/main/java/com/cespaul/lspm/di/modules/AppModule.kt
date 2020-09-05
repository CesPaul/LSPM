package com.cespaul.lspm.di.modules

import android.content.Context
import com.cespaul.lspm.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Singleton
    @Provides
    fun provideContext(): Context = app.applicationContext
}