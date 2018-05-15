package com.naver.wheejuni.dto.exceptions

import com.fasterxml.jackson.annotation.JsonProperty
import java.lang.RuntimeException

data class GeneralExceptionMessage(
        @field:JsonProperty("msg")
        val msg: String? = null) {

    companion object {
        fun fromException(exception: RuntimeException):GeneralExceptionMessage {
            return GeneralExceptionMessage(msg = exception.message?.plus("에러 메시지가 없습니다."))
        }
    }
}