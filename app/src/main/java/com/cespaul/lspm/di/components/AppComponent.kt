package com.cespaul.lspm.di.components

import android.content.Context
import com.cespaul.lspm.App
import com.cespaul.lspm.di.modules.AppModule
import dagger.Component
import javax.inject.Scope

@Scope
@Retention
annotation class AppScope

@AppScope
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {

    fun inject(app: App)

    fun appContext(): Context

    @Component.Factory
    interface Factory {
        fun create(appModule: AppModule): AppComponent
    }
}