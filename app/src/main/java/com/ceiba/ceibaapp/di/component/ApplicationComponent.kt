package com.ceiba.ceibaapp.di.component

import android.app.Application
import android.content.Context
import com.ceiba.ceibaapp.di.DatabaseInfo
import com.ceiba.ceibaapp.ui.view.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext

public interface ApplicationComponent {
    fun inject(mainActivity: MainActivity?)

    @ApplicationContext
    fun getContext(): Context?

    fun getApplication(): Application?

    @DatabaseInfo
    fun getDatabaseName(): String?
}