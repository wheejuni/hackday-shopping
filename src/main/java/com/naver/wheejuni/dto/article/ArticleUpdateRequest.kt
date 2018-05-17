package com.naver.wheejuni.dto.article

import com.fasterxml.jackson.annotation.JsonProperty
import com.naver.wheejuni.dto.UpdateRequestTypes
import com.naver.wheejuni.dto.fileupload.FileUploadResult

data class ArticleUpdateRequest(

        @field:JsonProperty("original_id")
        val id: Long = 0,

        @field:JsonProperty("requestType")
        val updateRequestTypes: UpdateRequestTypes = UpdateRequestTypes.UPDATE,

        @field:JsonProperty("content")
        val content: String? = null)