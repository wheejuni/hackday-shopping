package com.naver.wheejuni.dto.security

import com.fasterxml.jackson.annotation.JsonProperty
import com.naver.wheejuni.domain.UserGroups

data class UserLoginResult(
        @field:JsonProperty("username")
        val name: String? = null,

        @field:JsonProperty("usergroups")
        val groups: MutableSet<UserGroups>? = null,

        @field:JsonProperty("accessToken")
        val token: String? = null)