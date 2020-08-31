package com.cespaul.lspm.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cespaul.lspm.ui.pages.ListFragment
import com.cespaul.lspm.ui.pages.MapFragment
import com.cespaul.lspm.ui.pages.ParsingFragment
import com.cespaul.lspm.ui.pages.ScalingFragment

class CollectionStateAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> return ListFragment()
            1 -> return ScalingFragment()
            2 -> return ParsingFragment()
            3 -> return MapFragment()
            else -> ListFragment()
        }
    }

    override fun getItemCount(): Int = 4
}

