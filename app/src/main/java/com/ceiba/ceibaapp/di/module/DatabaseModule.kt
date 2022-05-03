package com.ceiba.ceibaapp.di.module

import android.content.Context
import androidx.room.Room
import com.ceiba.ceibaapp.Constants
import com.ceiba.ceibaapp.data.CeibaDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideLeagueDb(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        CeibaDb::class.java,
        Constants.NAME_DATA_BASE
    ).allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideUserDao(db: CeibaDb) = db.userDao()
}