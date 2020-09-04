package com.cespaul.lspm.base.activity

import android.content.Context

/**
 * Основа для базовых View.
 *
 */
interface BaseView {

    /**
     * Получение контекста.
     *
     * @return Возвращает контекст.
     */
    fun getContext(): Context
}