package com.ceiba.ceibaapp.domain

import com.ceiba.ceibaapp.data.UserRepository
import com.ceiba.ceibaapp.data.model.ItemUserDataState
import javax.inject.Inject

open class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke() = repository.getAllUsers()
    suspend operator fun invoke(user: ItemUserDataState.User) = repository.getUserPosts(user.id!!)
    suspend operator fun invoke(idUser: Int) = repository.getUser(idUser)
}