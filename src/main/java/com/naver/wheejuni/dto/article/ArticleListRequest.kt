package com.naver.wheejuni.dto.article

import com.fasterxml.jackson.annotation.JsonProperty
import com.naver.wheejuni.domain.validation.annotation.MinimalPageCount
import org.springframework.data.domain.PageRequest

data class ArticleListRequest(
        @field:JsonProperty("itemPerPage")
        @field:MinimalPageCount
        val itemCount: Int = 3,

        @field:JsonProperty("currentPage")
        val current: Int = 0) {

    fun toPageRequest(): PageRequest {
        return PageRequest.of(this.current, this.itemCount)
    }
}