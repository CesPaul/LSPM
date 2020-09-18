package com.cespaul.lspm.di.components

import com.cespaul.lspm.App
import com.cespaul.lspm.di.modules.AppModule
import com.cespaul.lspm.di.modules.RoomModule
import dagger.Component
import javax.inject.Scope

@Scope
@Retention
annotation class AppScope

@AppScope
@Component(
    modules = [
        AppModule::class,
        RoomModule::class
    ]
)
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        fun appModule(appModule: AppModule): Builder

        fun roomModule(roomModule: RoomModule): Builder
    }
}