package com.cespaul.lspm.di.components

import com.cespaul.lspm.di.modules.DaoModule
import com.cespaul.lspm.di.modules.NetworkModule
import com.cespaul.lspm.ui.pages.list.ListPresenter
import com.cespaul.lspm.ui.pages.map.MapPresenter
import com.cespaul.lspm.ui.pages.parsing.ParsingPresenter
import com.cespaul.lspm.ui.pages.scailng.ScalingPresenter
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

    fun inject(scalingPresenter: ScalingPresenter)

    fun inject(parsingPresenter: ParsingPresenter)

    fun inject(mapPresenter: MapPresenter)

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): ScreensComponent
    }
}
