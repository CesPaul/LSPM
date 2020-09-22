package com.cespaul.lspm.di.modules

import android.content.Context
import androidx.room.Room
import com.cespaul.lspm.data.database.AppDatabase
import com.cespaul.lspm.data.database.ListDao
import com.cespaul.lspm.di.components.ScreensScope
import dagger.Module
import dagger.Provides

@Module
class RoomModule {

    @ScreensScope
    @Provides
    fun provideDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "main_repository"
    )
        .allowMainThreadQueries()
        .build()

    @ScreensScope
    @Provides
    fun provideListDao(appDatabase: AppDatabase): ListDao = appDatabase.listDao()
}
