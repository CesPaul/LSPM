package com.cespaul.lspm.base.fragment

import com.cespaul.lspm.App
import com.cespaul.lspm.di.components.DaggerScreensComponent
import com.cespaul.lspm.ui.pages.list.ListPresenter

abstract class BaseFragmentPresenter<out V : BaseFragmentView>(protected val viewFragment: V) {

    private val screensComponent = DaggerScreensComponent
        .factory()
        .create(App.appComponent)

    init {
        inject()
    }

    open fun onViewCreated() {}

    open fun onViewDestroyed() {}

    private fun inject() {
        when (this) {
            is ListPresenter -> screensComponent.inject(this)
        }
    }
}