package com.ceiba.ceibaapp.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

sealed class ItemUserDataState {
    data class Error(val message: String?) : ItemUserDataState()

    @Entity
    data class User(
        @PrimaryKey
        val id: Int?,
        @Embedded
        val address: Address?,
        @Embedded
        val company: Company?,
        val email: String?,
        val name: String?,
        val phone: String?,
        val username: String?,
        val website: String?
    )

    @Entity
    data class Address(
        val city: String?,
        @Embedded
        val geo: Geo?,
        val street: String?,
        val suite: String?,
        val zipcode: String?
    )

    @Entity
    data class Company(
        val bs: String?,
        val catchPhrase: String?,
    )

    @Entity
    data class Geo(
        val lat: String?,
        val lng: String?
    )
}