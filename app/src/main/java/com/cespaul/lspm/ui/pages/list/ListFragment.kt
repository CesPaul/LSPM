package com.cespaul.lspm.ui.pages.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cespaul.lspm.R
import com.cespaul.lspm.base.fragment.BaseFragment

class ListFragment : BaseFragment<ListPresenter>(), ListView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun instantiatePresenter(): ListPresenter {
        return ListPresenter(this)
    }

}