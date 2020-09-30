package com.cespaul.lspm.ui

import android.content.Context
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.cespaul.lspm.R
import com.cespaul.lspm.base.activity.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Главное окно приложения.
 *
 */
class MainActivity : BaseActivity<MainPresenter>(), MainView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewPagerInit(mainPager)
        bottomNavigationInit(mainBottomNavigation, mainPager)

    }

    /**
     * Инициализация ViewPager2.
     *
     * @param collectionViewPager
     */
    private fun viewPagerInit(collectionViewPager: ViewPager2) {
        collectionViewPager.isUserInputEnabled = false
        collectionViewPager.adapter = CollectionStateAdapter(this, this)
    }

    /**
     * Инициализация BottomNavigationView.
     *
     * @param bottomNavigationView
     * @param collectionViewPager
     */
    private fun bottomNavigationInit(
        bottomNavigationView: BottomNavigationView,
        collectionViewPager: ViewPager2
    ) {
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

    override fun getContext(): Context {
        return this
    }

    override fun instantiatePresenter(): MainPresenter {
        return MainPresenter(this)
    }

    override fun initSnackbar(message: String, isIndefiniteLength: Boolean): Snackbar {
        return when (isIndefiniteLength) {
            true -> Snackbar.make(
                mainBottomNavigation,
                message,
                Snackbar.LENGTH_INDEFINITE
            ).setAnchorView(mainBottomNavigation)
            false -> Snackbar.make(
                mainBottomNavigation,
                message,
                Snackbar.LENGTH_LONG
            ).setAnchorView(mainBottomNavigation)
        }
    }

    companion object {
        const val REQUEST_IMAGE_GALLERY = 1

        const val REQUEST_IMAGE_CAPTURE = 2
    }
}