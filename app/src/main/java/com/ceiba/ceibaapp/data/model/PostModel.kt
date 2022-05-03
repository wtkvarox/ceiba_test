package com.ceiba.ceibaapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

sealed class ItemPostDataState {
    data class Error(val message: String?) : ItemPostDataState()

    @Entity
    data class PostModel(
        val results: List<Post>,
    )

    @Entity
    data class Post(
        @PrimaryKey
        val userId: Int?,
        val id: Int?,
        val title: String?,
        val body: String?
    )
}