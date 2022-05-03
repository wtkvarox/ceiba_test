package com.ceiba.ceibaapp.data.network

import com.ceiba.ceibaapp.data.model.ItemUserDataState
import com.ceiba.ceibaapp.data.model.ItemPostDataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserService @Inject constructor(private val api: UserApiClient) {
    suspend fun getUsers(): List<ItemUserDataState.User> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllUsers()
            response.body() ?: emptyList()
        }
    }

    suspend fun getUserPosts(idUser: Int): List<ItemPostDataState.Post> {
        return withContext(Dispatchers.IO) {
            val response = api.getUserPosts(idUser)
            response.body() ?: emptyList()
        }
    }
}