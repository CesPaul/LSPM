package com.cespaul.lspm.ui

import com.cespaul.lspm.base.activity.BasePresenter
import com.cespaul.lspm.base.activity.BaseView

/**
 * Отвечает за логику работы приложения и взаимодействия.
 *
 * @constructor
 * View экрана
 *
 * @param mainView
 */
class MainPresenter(mainView: MainView) : BasePresenter<BaseView>(mainView)