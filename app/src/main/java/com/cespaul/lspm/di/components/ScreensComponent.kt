package com.cespaul.lspm.di.components

import com.cespaul.lspm.di.modules.DaoModule
import com.cespaul.lspm.di.modules.NetworkModule
import com.cespaul.lspm.ui.pages.list.ListPresenter
import com.cespaul.lspm.ui.pages.parsing.ParsingPresenter
import dagger.Component
import javax.inject.Scope

@Scope
@Retention
annotation class ScreensScope

@ScreensScope
@Component(
    dependencies = [AppComponent::class],
    modules = [
        DaoModule::class,
        NetworkModule::class
    ]
)
interface ScreensComponent {

    fun inject(listPresenter: ListPresenter)

    fun inject(parsingPresenter: ParsingPresenter)

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): ScreensComponent
    }
}
