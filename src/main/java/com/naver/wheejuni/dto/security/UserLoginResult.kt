package com.naver.wheejuni.dto.security

import com.fasterxml.jackson.annotation.JsonProperty
import com.naver.wheejuni.domain.UserGroups
import com.naver.wheejuni.security.tokens.PostAuthorizeToken

data class UserLoginResult(
        @field:JsonProperty("username")
        val name: String? = null,

        @field:JsonProperty("usergroups")
        val groups: MutableSet<UserGroups>? = null,

        @field:JsonProperty("accessToken")
        val token: String? = null) {

        companion object {
            fun fromToken(token: PostAuthorizeToken, jwt:String): UserLoginResult {
                val account = token.account
                return UserLoginResult(name = account.name, groups = account.userGroups, token = jwt)
            }
        }
}