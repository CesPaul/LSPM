package com.cespaul.lspm.base.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

/**
 * Обеспечивает основу для конкретных презентеров.
 *
 * @param V Конкретная реализация базовой View фрагмента.
 * @property view Конкретная реализация базовой View фрагмента.
 */
abstract class BaseFragment<P : BaseFragmentPresenter<BaseFragmentView>> : BaseFragmentView,
    Fragment() {
    protected lateinit var presenter: P

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = instantiatePresenter()
    }

    /**
     * Создание презентера.
     * Вызывается в методе onCreate.
     *
     * @return Экземпляр конкретной реализации базового презентера.
     */
    protected abstract fun instantiatePresenter(): P
}