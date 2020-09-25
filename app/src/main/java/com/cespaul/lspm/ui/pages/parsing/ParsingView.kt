package com.cespaul.lspm.ui.pages.parsing

import com.cespaul.lspm.base.fragment.BaseFragmentView
import com.cespaul.lspm.model.Quotes

interface ParsingView : BaseFragmentView {

    fun updateQuotes(quotes: Quotes)

    fun showProgress()

    fun hideProgress()

    fun showSnackbar()

    fun hideSnackbar()

    fun initSnackbar()
}