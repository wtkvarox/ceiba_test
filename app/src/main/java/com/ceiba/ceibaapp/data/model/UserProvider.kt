package com.ceiba.ceibaapp.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserProvider @Inject constructor() {
    var users: List<ItemUserDataState.User> = emptyList()
    var posts: List<ItemPostDataState.Post> = emptyList()
}