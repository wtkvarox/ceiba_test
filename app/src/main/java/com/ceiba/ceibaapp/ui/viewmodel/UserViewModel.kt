package com.ceiba.ceibaapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceiba.ceibaapp.data.model.ItemUserDataState
import com.ceiba.ceibaapp.data.model.ItemPostDataState
import com.ceiba.ceibaapp.domain.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    val usersModel = MutableLiveData<List<ItemUserDataState.User>>()
    val userModel = MutableLiveData<ItemUserDataState.User>()
    val userPostsModel = MutableLiveData<List<ItemPostDataState.Post>>()
    val user = MutableLiveData<ItemUserDataState.User>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        getListUsers()
    }

    fun getListUsers() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result: List<ItemUserDataState.User> = getUserUseCase.invoke()

            usersModel.postValue(result)
            isLoading.postValue(false)
        }
    }

    fun getUserPosts(user: ItemUserDataState.User) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getUserUseCase.invoke(user)

            userPostsModel.postValue(result)
            isLoading.postValue(false)
        }
    }

    fun getUser(userId: Int) {
        viewModelScope.launch {
            val result = getUserUseCase.invoke(idUser = userId)
            userModel.postValue(result)
        }
    }
}