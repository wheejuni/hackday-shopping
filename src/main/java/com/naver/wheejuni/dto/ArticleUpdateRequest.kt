package com.naver.wheejuni.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ArticleUpdateRequest(

        @field:JsonProperty("newTitle")
        val title: String? = null,

        @field:JsonProperty("content")
        val content: String? = null
)