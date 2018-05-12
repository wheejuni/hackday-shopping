package com.naver.wheejuni.dto.security

import com.fasterxml.jackson.annotation.JsonProperty
import com.naver.wheejuni.security.tokens.PreAuthorizeToken

data class UserLoginRequest(
        @field:JsonProperty("userid")
        val id: String? = null,

        @field:JsonProperty("password")
        val password: String? = null) {

    fun toToken(): PreAuthorizeToken {
        return PreAuthorizeToken(this)
    }
}