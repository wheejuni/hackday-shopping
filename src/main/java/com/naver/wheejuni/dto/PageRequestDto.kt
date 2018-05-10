package com.naver.wheejuni.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class PageRequestDto(
        @field:JsonProperty("countPerPage")
        val count: Int? = 5)