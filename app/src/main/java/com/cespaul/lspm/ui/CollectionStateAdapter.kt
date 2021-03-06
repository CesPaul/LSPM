package com.cespaul.lspm.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cespaul.lspm.ui.pages.list.ListFragment
import com.cespaul.lspm.ui.pages.map.MapFragment
import com.cespaul.lspm.ui.pages.parsing.ParsingFragment
import com.cespaul.lspm.ui.pages.scailng.ScalingFragment

class CollectionStateAdapter(private val mainView: MainView, fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> return ListFragment()
            1 -> return ScalingFragment()
            2 -> return ParsingFragment(mainView)
            3 -> return MapFragment(mainView)
            else -> ListFragment()
        }
    }

    override fun getItemCount(): Int = 4
}

