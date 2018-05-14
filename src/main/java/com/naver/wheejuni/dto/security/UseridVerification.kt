package com.naver.wheejuni.dto.security

import com.fasterxml.jackson.annotation.JsonProperty

data class UseridVerification(

        @field:JsonProperty("requestedId")
        val id: String? = null,

        @field:JsonProperty("valid")
        val valid: Boolean = false) {

    companion object {

        fun ofValid(userid: String): UseridVerification {
            return UseridVerification(userid, true)
        }

        fun ofInvalid(userid: String): UseridVerification {
            return UseridVerification(userid, false)
        }
    }
}