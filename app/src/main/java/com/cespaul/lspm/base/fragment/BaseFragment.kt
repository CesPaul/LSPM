package com.cespaul.lspm.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

/**
 * Обеспечивает основу для активити.
 *
 * @param P Для передачи базового презентера.
 */
abstract class BaseFragment<P : BaseFragmentPresenter<BaseFragmentView>> :
    BaseFragmentView,
    Fragment() {
    protected lateinit var presenter: P

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = instantiatePresenter()
    }

    /**
     * Создание презентера.
     *
     * @return Экземпляр конкретной реализации базового презентера.
     */
    protected abstract fun instantiatePresenter(): P
    override fun getFragmentContext(): Context {
        return requireContext()
    }
}