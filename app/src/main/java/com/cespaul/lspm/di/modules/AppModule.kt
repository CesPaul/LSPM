package com.cespaul.lspm.di.modules

import android.content.Context
import androidx.room.Room
import com.cespaul.lspm.App
import com.cespaul.lspm.data.database.AppDatabase
import com.cespaul.lspm.di.components.AppScope
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: App) {

    @AppScope
    @Provides
    fun provideContext(): Context = app.applicationContext

    @AppScope
    @Provides
    fun provideDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "app_database"
    )
        .allowMainThreadQueries()
        .build()
}