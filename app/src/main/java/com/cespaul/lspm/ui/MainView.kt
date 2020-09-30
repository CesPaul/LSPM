package com.cespaul.lspm.ui

import com.cespaul.lspm.base.activity.BaseView
import com.google.android.material.snackbar.Snackbar

interface MainView : BaseView {

    fun initSnackbar(message: String, isIndefiniteLength: Boolean): Snackbar
}