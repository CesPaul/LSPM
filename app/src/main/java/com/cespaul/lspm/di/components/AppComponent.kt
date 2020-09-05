package com.cespaul.lspm.di.components

import com.cespaul.lspm.App
import com.cespaul.lspm.di.modules.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class]
)
interface AppComponent {

    fun inject(app: App)

    @Component.Factory
    interface Factory {
        fun create(appModule: AppModule): AppComponent
    }
}