package com.naver.wheejuni.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.naver.wheejuni.domain.Account

data class UserJoinRequest(

        @field:JsonProperty("id")
        val id: String? = null,

        @field:JsonProperty("password")
        val password: String? = null,

        @field:JsonProperty("selectedGroups")
        val groups: MutableSet<String>? = null) {

    fun toModel(): Account {
        return Account.fromRequest(this)
    }
}