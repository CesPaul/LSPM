package com.cespaul.lspm.base.fragment

abstract class BaseFragmentPresenter<out V : BaseFragmentView>(protected val viewFragment: V) {

    open fun onViewCreated() {}

    open fun onViewDestroyed() {}

}