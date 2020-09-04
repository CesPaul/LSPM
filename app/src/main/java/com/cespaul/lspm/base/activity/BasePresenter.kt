package com.cespaul.lspm.base.activity

/**
 * Обеспечивает основу для конкретных презентеров.
 *
 * @param V Конкретная реализация базовой View.
 * @property view Конкретная реализация базовой View.
 */
abstract class BasePresenter<out V : BaseView>(protected val view: BaseView) {


    open fun onViewCreated() {}

    open fun onViewDestroyed() {}
}