package com.naver.wheejuni.dto.security

import com.fasterxml.jackson.annotation.JsonProperty

data class UseridVerificationRequest(
        @field:JsonProperty("id")
        val id: String? = null)