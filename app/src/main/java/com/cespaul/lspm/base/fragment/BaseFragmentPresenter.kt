package com.cespaul.lspm.base.fragment

import com.cespaul.lspm.di.components.DaggerScreensComponent
import com.cespaul.lspm.di.components.ScreensComponent
import com.cespaul.lspm.di.modules.RoomModule
import com.cespaul.lspm.ui.pages.list.ListPresenter

abstract class BaseFragmentPresenter<out V : BaseFragmentView>(protected val viewFragment: V) {

    private val screensComponent: ScreensComponent = DaggerScreensComponent
        .builder()
        .baseFragmentView(viewFragment)
        .roomModule(RoomModule())
        .build()

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