package com.naver.wheejuni.dto.article

import com.fasterxml.jackson.annotation.JsonProperty

data class PagedArticles(
        @field:JsonProperty("currentPage")
        val page: Int = 0,

        @field:JsonProperty("totalPageRequested")
        val totalPage: Int = 0,

        @field:JsonProperty("items")
        val items: MutableList<ArticleListView>? = null)