package com.cespaul.lspm.base.fragment

abstract class BaseFragmentPresenter<out V : BaseFragmentView>(protected val viewFragment: BaseFragmentView) {

    open fun onViewCreated() {}

    open fun onViewDestroyed() {}

}