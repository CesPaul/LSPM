package com.cespaul.lspm.di.components

import javax.inject.Scope

@Scope
@Retention
annotation class ListScreenScope

/*
@Module
class ListScreenModule {

    @ListScreenScope
    @Provides
    fun providePresenter(
        listView: ListView,
        listRepository: ListRepository
    ): ListPresenter = ListPresenter(listView, listRepository)
}

@ListScreenScope
@Component(dependencies = [AppComponent::class], modules = [ListScreenModule::class])
interface ListScreenComponent {

    fun inject(fragment: ListFragment)

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): ListScreenComponent
    }
}*/
