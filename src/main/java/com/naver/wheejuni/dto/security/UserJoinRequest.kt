package com.naver.wheejuni.dto.security

import com.fasterxml.jackson.annotation.JsonProperty
import com.naver.wheejuni.domain.Account
import org.springframework.security.crypto.password.PasswordEncoder

data class UserJoinRequest(

        @field:JsonProperty("id")
        val id: String? = null,

        @field:JsonProperty("password")
        val password: String? = null,

        @field:JsonProperty("selectedGroups")
        val groups: MutableSet<String>? = null) {

    fun toModel(passwordEncoder: PasswordEncoder): Account {
        return Account.fromRequest(this, passwordEncoder)
    }
}