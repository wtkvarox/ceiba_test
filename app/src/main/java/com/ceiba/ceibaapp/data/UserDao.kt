package com.ceiba.ceibaapp.data

import androidx.room.*
import com.ceiba.ceibaapp.data.model.ItemUserDataState.User
import com.ceiba.ceibaapp.data.model.ItemPostDataState.Post

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun findAllUsers(): List<User>

    @Query("SELECT * FROM Post WHERE userId IN (:userId)")
    fun findAllUserPosts(userId: Int): List<Post>

    @Query("SELECT * FROM User WHERE id IN (:idUser)")
    fun findUser(idUser: Int): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUsers(users: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPosts(posts: List<Post>)
}

@Database(
    entities = [Post::class, User::class],
    version = 2
)
abstract class CeibaDb : RoomDatabase() {
    abstract fun userDao(): UserDao
}