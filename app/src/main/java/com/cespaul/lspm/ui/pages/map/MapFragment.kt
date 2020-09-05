package com.cespaul.lspm.ui.pages.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cespaul.lspm.R
import com.cespaul.lspm.base.fragment.BaseFragment

class MapFragment : BaseFragment<MapPresenter>(), MapView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun instantiatePresenter(): MapPresenter {
        return MapPresenter(this)
    }

}