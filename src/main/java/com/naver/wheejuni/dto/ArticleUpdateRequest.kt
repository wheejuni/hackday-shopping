package com.naver.wheejuni.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.naver.wheejuni.dto.fileupload.FileUploadResult

data class ArticleUpdateRequest(

        @field:JsonProperty("original_id")
        val id: Long = 0,

        @field:JsonProperty("requestType")
        val updateRequestTypes: UpdateRequestTypes = UpdateRequestTypes.UPDATE,

        @field:JsonProperty("newTitle")
        val title: String? = null,

        @field:JsonProperty("content")
        val content: String? = null,

        @field:JsonProperty("attachedFile")
        val file: MutableList<FileUploadResult>? = null)