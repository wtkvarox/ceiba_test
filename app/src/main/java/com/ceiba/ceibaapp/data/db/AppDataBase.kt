package com.ceiba.ceibaapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ceiba.ceibaapp.data.UserDao
import com.ceiba.ceibaapp.data.model.ItemUserDataState
import com.ceiba.ceibaapp.data.model.ItemPostDataState

@Database(entities = [ItemUserDataState.User::class, ItemPostDataState.Post::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao?
}