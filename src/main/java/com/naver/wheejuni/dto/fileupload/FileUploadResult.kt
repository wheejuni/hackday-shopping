package com.naver.wheejuni.dto.fileupload

import com.fasterxml.jackson.annotation.JsonProperty
import com.naver.wheejuni.domain.File

data class FileUploadResult(
        @field:JsonProperty("originalFileName")
        val original: String? = null,

        @field:JsonProperty("encodedFileName")
        val encoded: String? = null) {

    fun toModel(): File {
        return File(original, encoded)
    }
}