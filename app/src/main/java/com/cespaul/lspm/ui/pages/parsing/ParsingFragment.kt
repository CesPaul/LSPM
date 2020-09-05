package com.cespaul.lspm.ui.pages.parsing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cespaul.lspm.R
import com.cespaul.lspm.base.fragment.BaseFragment

class ParsingFragment : BaseFragment<ParsingPresenter>(), ParsingView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_parsing, container, false)
    }

    override fun instantiatePresenter(): ParsingPresenter {
        return ParsingPresenter(this)
    }

}