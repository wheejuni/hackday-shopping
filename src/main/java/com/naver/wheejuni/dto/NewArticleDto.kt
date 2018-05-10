package com.naver.wheejuni.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.naver.wheejuni.domain.File
import com.naver.wheejuni.domain.UserGroups

data class NewArticleDto(

        @field:JsonProperty("title")
        val title: String? = null,

        @field:JsonProperty("content")
        val content: String? = null,

        @field:JsonProperty("targetGroups")
        val groups: MutableSet<UserGroups>? = null,

        @field:JsonProperty("fileHref")
        val file: File? = null
)