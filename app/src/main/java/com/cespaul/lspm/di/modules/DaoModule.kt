package com.cespaul.lspm.di.modules

import com.cespaul.lspm.data.database.AppDatabase
import com.cespaul.lspm.data.database.ListDao
import com.cespaul.lspm.di.components.ScreensScope
import dagger.Module
import dagger.Provides

@Module
class DaoModule {

    @ScreensScope
    @Provides
    fun provideListDao(appDatabase: AppDatabase): ListDao = appDatabase.listDao()
}
