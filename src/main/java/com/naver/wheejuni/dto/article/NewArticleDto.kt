package com.naver.wheejuni.dto.article

import com.fasterxml.jackson.annotation.JsonProperty
import com.naver.wheejuni.domain.UserGroups
import com.naver.wheejuni.dto.fileupload.FileUploadResult

data class NewArticleDto(

        @field:JsonProperty("title")
        val title: String? = null,

        @field:JsonProperty("content")
        val content: String? = null,

        @field:JsonProperty("targetGroups")
        val groups: MutableSet<UserGroups>? = null,

        @field:JsonProperty("fileHref")
        val file: MutableSet<FileUploadResult>? = null)