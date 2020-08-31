package com.cespaul.lspm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.cespaul.lspm.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val collectionViewPager: ViewPager2 = mainPager
        collectionViewPager.isUserInputEnabled = false
        collectionViewPager.adapter = CollectionStateAdapter(this)

        val bottomNavigationView = mainBottomNavigation
        bottomNavigationView.inflateMenu(R.menu.bottom_nav_menu)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.list_item -> {
                    collectionViewPager.currentItem = 0
                }
                R.id.scaling_item -> {
                    collectionViewPager.currentItem = 1
                }
                R.id.parsing_item -> {
                    collectionViewPager.currentItem = 2
                }
                R.id.map_item -> {
                    collectionViewPager.currentItem = 3
                }
            }
            true
        }
    }
}