package com.cespaul.lspm.ui.pages.parsing

import com.cespaul.lspm.base.fragment.BaseFragmentView
import com.cespaul.lspm.model.Quotes

interface ParsingView : BaseFragmentView {

    fun updateQuotes(quotes: Quotes)

    fun visibilityProgressBar(isVisible: Boolean)

    fun initAndShowSnackbar()
}