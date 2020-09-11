package com.cespaul.lspm.di.components

import android.content.Context
import com.cespaul.lspm.App
import com.cespaul.lspm.di.modules.AppModule
import com.cespaul.lspm.di.modules.RepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    fun inject(app: App)

    fun appContext(): Context
    //fun listRepository(): ListRepository

    @Component.Factory
    interface Factory {
        fun create(appModule: AppModule): AppComponent
    }
}