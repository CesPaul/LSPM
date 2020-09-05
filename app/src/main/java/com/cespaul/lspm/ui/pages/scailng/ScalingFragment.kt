package com.cespaul.lspm.ui.pages.scailng

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cespaul.lspm.R
import com.cespaul.lspm.base.fragment.BaseFragment

class ScalingFragment : BaseFragment<ScalingPresenter>(), ScalingView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scaling, container, false)
    }

    override fun instantiatePresenter(): ScalingPresenter {
        return ScalingPresenter(this)
    }

}