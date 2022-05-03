package com.ceiba.ceibaapp.data.network

import com.ceiba.ceibaapp.data.model.ItemUserDataState
import com.ceiba.ceibaapp.data.model.ItemPostDataState
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiClient {
    @GET("users")
    suspend fun getAllUsers(): Response<List<ItemUserDataState.User>>

    @GET("posts")
    suspend fun getUserPosts(@Query("userId") idUser: Int): Response<List<ItemPostDataState.Post>>
}