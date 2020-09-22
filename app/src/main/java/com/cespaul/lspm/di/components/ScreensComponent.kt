package com.cespaul.lspm.di.components

import com.cespaul.lspm.di.modules.RoomModule
import com.cespaul.lspm.ui.pages.list.ListPresenter
import dagger.Component
import javax.inject.Scope

@Scope
@Retention
annotation class ScreensScope

@ScreensScope
@Component(
    dependencies = [AppComponent::class],
    modules = [
        RoomModule::class
    ]
)
interface ScreensComponent {

    fun inject(listPresenter: ListPresenter)

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): ScreensComponent
    }
}
