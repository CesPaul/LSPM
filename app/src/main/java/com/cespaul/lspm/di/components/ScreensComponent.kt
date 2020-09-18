package com.cespaul.lspm.di.components

import com.cespaul.lspm.base.fragment.BaseFragmentView
import com.cespaul.lspm.di.modules.RoomModule
import com.cespaul.lspm.ui.pages.list.ListPresenter
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [RoomModule::class]
)
interface ScreensComponent {
    fun inject(listPresenter: ListPresenter)

    @Component.Builder
    interface Builder {
        fun build(): ScreensComponent

        fun roomModule(roomModule: RoomModule): Builder

        @BindsInstance
        fun baseFragmentView(baseFragmentView: BaseFragmentView): Builder
    }
}
