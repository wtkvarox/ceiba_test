package com.ceiba.ceibaapp.data

import com.ceiba.ceibaapp.data.model.ItemUserDataState
import com.ceiba.ceibaapp.data.model.ItemPostDataState
import com.ceiba.ceibaapp.data.model.UserProvider
import com.ceiba.ceibaapp.data.network.UserService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserService,
    private val userProvider: UserProvider,
    private val userDao: UserDao
) {

    suspend fun getAllUsers(): List<ItemUserDataState.User> {

        val localData: List<ItemUserDataState.User> = userDao.findAllUsers()

        return localData.ifEmpty {
            val response: List<ItemUserDataState.User> = api.getUsers()
            userDao.insertAllUsers(response)
            userProvider.users = response
            response
        }
    }

    suspend fun getUserPosts(userId: Int): List<ItemPostDataState.Post> {
        val response = api.getUserPosts(userId)
        userProvider.posts = response

        return response
    }

    suspend fun getUser(idUser: Int): ItemUserDataState.User {
        return userDao.findUser(idUser)
    }
}